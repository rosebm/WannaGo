package com.rosalynbm.wannago.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rosalynbm.wannago.R
import com.rosalynbm.wannago.base.BaseFragment
import com.rosalynbm.wannago.databinding.FragmentPlaceBinding
import com.rosalynbm.wannago.model.Place
import com.rosalynbm.wannago.ui.poilist.PoisListViewModel
import com.rosalynbm.wannago.util.setup
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaceFragment : BaseFragment() {
    private var place: Place? = null
    private lateinit var binding: FragmentPlaceBinding

    override val _viewModel: PoisListViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlaceBinding.inflate(inflater)
        binding.lifecycleOwner = this

        place = arguments?.let { PlaceFragmentArgs.fromBundle(it).selectedPlace }

        place?.let { place ->
            bindTextViewToName(binding.placeNameText, place.name)

            place.rating?.let {
                bindTextViewToRating(binding.placeRatingText, it.toString())
                bindRatingBarToRating(binding.placeRatingBar, it)
            }

            place.types?.let {
                bindTextViewToType(binding.placeTypeText, it[0] + " " + it[1])
            }

            place.photos?.let { list ->
                val reference = list[0].photo_reference
                val photoUrl = _viewModel.getPhotoUrl(reference)
                bindImageViewToName(binding.image, photoUrl)
            }

            place.reviews?.let {
                _viewModel.loadReviews(it)
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = ReviewListAdapter {
        }

        // Setup the recycler view using the extension function
        binding.placeReviewsRecyclerview.setup(adapter)
    }

}