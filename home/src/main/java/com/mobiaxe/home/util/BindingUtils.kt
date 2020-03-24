package com.mobiaxe.home.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

@BindingAdapter("app:coverBig")
fun setCoverBig(imageView: ImageView, hash: String?) {
    val context = imageView.context
    Glide.with(context)
        .load("https://images.igdb.com/igdb/image/upload/t_cover_big/$hash.jpg")
        .apply(RequestOptions().override(Target.SIZE_ORIGINAL))
        .into(imageView)
}