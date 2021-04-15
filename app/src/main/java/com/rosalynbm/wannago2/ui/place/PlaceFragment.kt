package com.rosalynbm.wannago2.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.rosalynbm.wannago2.base.BaseFragment
import com.rosalynbm.wannago2.databinding.FragmentPlaceBinding
import com.rosalynbm.wannago2.util.setup
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PlaceFragment : BaseFragment() {
    private lateinit var binding: FragmentPlaceBinding

    override val _viewModel: PlaceViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlaceBinding.inflate(inflater)
        //setupRecyclerView()
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.placeDetailsMotionLayout.transitionToEnd()

        val placeId = arguments?.let { PlaceFragmentArgs.fromBundle(it).placeId }

        placeId?.let {
            lifecycleScope.launch {
                Timber.d("Poi selected: $placeId")
                val placeDetails = _viewModel.getPlaceDetails(placeId)
                placeDetails?.let { place ->
                    bindTextViewToName(binding.placeNameText, place.name)

                    place.rating?.let {
                        bindTextViewToRating(binding.placeRatingText, it.toString())
                        bindRatingBarToRating(binding.placeRatingBar, it)
                    }

                    place.types?.let {
                        bindTextViewToType(binding.placeTypeText, it[0] + " " + it[1])
                    }

                    place.formatted_address?.let {
                        bindTextViewToAddress(binding.placeAddressText, it)
                    }

                    place.photos?.let { list ->
                        val reference = list[0].photo_reference
                        val photoUrl = reference?.let { _viewModel.getPhotoUrl(it) }
                        photoUrl?.let {
                            bindImageViewToName(binding.image, photoUrl)
                        }
                    }

                    /*place.reviews?.let {
                        _viewModel.loadReviews(it)
                    }*/
                }
            }
        }


    }

    /*private fun setupRecyclerView() {
        val adapter = ReviewListAdapter {
        }
        // Setup the recycler view using the extension function
        binding.placeReviewsRecyclerview.setup(adapter)
    }*/

}