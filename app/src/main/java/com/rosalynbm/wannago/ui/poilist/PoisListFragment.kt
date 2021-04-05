package com.rosalynbm.wannago.ui.poilist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.rosalynbm.wannago.R
import com.rosalynbm.wannago.base.BaseFragment
import com.rosalynbm.wannago.base.NavigationCommand
import com.rosalynbm.wannago.databinding.FragmentPoisListBinding
import com.rosalynbm.wannago.model.Place
import com.rosalynbm.wannago.util.setup
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A fragment representing a list of Items.
 */
class PoisListFragment : BaseFragment() {

    private lateinit var binding: FragmentPoisListBinding

    override val _viewModel: PoisListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pois_list, container, false)
        binding.viewModel = _viewModel

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

            lifecycleScope.launch {
                Timber.d("Poi selected: ${placeSelected.placeId}")
                placeSelected.placeId?.let {
                    val place = _viewModel.getPlaceDetails(it)
                    navigateToPlaceFragment(place)
                }
            }
        }

        // Setup the recycler view using the extension function
        binding.placesRecyclerView.setup(adapter)
    }

}