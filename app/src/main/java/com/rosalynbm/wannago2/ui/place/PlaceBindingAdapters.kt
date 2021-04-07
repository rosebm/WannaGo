package com.rosalynbm.wannago2.ui.place

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.size.Scale
import com.rosalynbm.wannago2.R

@BindingAdapter("image")
fun bindImageViewToName(imageview: ImageView, photoUrl: String) {
    imageview.load(photoUrl) {
        placeholder(R.drawable.ic_placeholder)
        //transformations(RoundedCornersTransformation())
        scale(Scale.FILL)
    }
}

@BindingAdapter("placeNameText")
fun bindTextViewToName(textView: TextView, name: String) {
    textView.text = String.format(name)
}

@BindingAdapter("placeRatingText")
fun bindTextViewToRating(textView: TextView, number: String) {
    textView.text = String.format(number)
}

@BindingAdapter("placeRatingBar")
fun bindRatingBarToRating(ratingBar: RatingBar, number: Float) {
    ratingBar.rating = number
}

@BindingAdapter("placeTypeText")
fun bindTextViewToType(textView: TextView, type: String) {
    textView.text = String.format(type)
}

