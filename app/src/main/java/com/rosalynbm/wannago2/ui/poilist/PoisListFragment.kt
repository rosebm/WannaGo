package com.rosalynbm.wannago2.ui.poilist

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.firebase.ui.auth.AuthUI
import com.rosalynbm.wannago2.R
import com.rosalynbm.wannago2.base.BaseFragment
import com.rosalynbm.wannago2.base.NavigationCommand
import com.rosalynbm.wannago2.databinding.FragmentPoisListBinding
import com.rosalynbm.wannago2.model.Place
import com.rosalynbm.wannago2.ui.HolderActivity
import com.rosalynbm.wannago2.ui.login.LoginViewModel
import com.rosalynbm.wannago2.util.Variables
import com.rosalynbm.wannago2.util.setup
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A fragment representing a list of Items.
 */
class PoisListFragment : BaseFragment() {

    private lateinit var binding: FragmentPoisListBinding

    override val _viewModel: PoisListViewModel by viewModel()
    private val loginViewModel: LoginViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pois_list,
            container, false)
        binding.viewModel = _viewModel
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        setupRecyclerView()
        binding.placesAddButton.setOnClickListener {
            navigateToMapFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // Display logout as menu item
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onResume() {
        super.onResume()
        //load the reminders list on the ui
        _viewModel.loadPlaces()
    }

    private fun navigateToMapFragment() {
        // Use the navigationCommand live data to navigate between the fragments
        _viewModel.navigationCommand.postValue(
            NavigationCommand.To(
                    PoisListFragmentDirections.toMapsFragment()))
    }

    private fun navigateToPlaceFragment(place: Place) {
        // Use the navigationCommand live data to navigate between the fragments
        val action = PoisListFragmentDirections.toPlaceFragment(place)
        _viewModel.navigationCommand.postValue(
                NavigationCommand.To(action))
    }

    private fun setupRecyclerView() {
        val adapter = PoisListAdapter { placeSelected ->
            placeSelected.placeId?.let { checkNetworkAndGetPlace(it) }
        }

        // Setup the recycler view using the extension function
        binding.placesRecyclerView.setup(adapter)
    }

    private fun checkNetworkAndGetPlace(placeId: String) {
        if (Variables.isNetworkConnected) {
            lifecycleScope.launch {
                Timber.d("Poi selected: $placeId")
                val place = _viewModel.getPlaceDetails(placeId)
                place?.let { navigateToPlaceFragment(it) }
            }
        } else
            _viewModel.showSnackBar.value = getString(R.string.no_internet_connection)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                AuthUI.getInstance()
                    .signOut(requireContext())
                    .addOnCompleteListener {
                        // User is now signed out
                        loginViewModel.setUserAuthenticated(false)
                        navigateLogin()
                    }
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun navigateLogin() {
        // Use the navigationCommand live data to navigate between the fragments
        startActivity(Intent(requireContext(), HolderActivity::class.java))
        requireActivity().finish()
    }


}