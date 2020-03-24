package com.mobiaxe.onboarding.util

import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import com.bumptech.glide.request.target.Target
import com.mobiaxe.core.extension.px


@BindingAdapter("isSelected")
fun MaterialCardView.setSelected(isSelected: Boolean) {
    isChecked = isSelected
    isHovered = isSelected
    isPressed = isSelected
}

@BindingAdapter("imageDrawable")
fun setImageDrawable(imageView: ImageView, res: Int?) {
    val context = imageView.context
    Glide.with(context).load(res).apply(RequestOptions().override(Target.SIZE_ORIGINAL)).into(imageView)
}

@BindingAdapter("increaseClickingArea")
fun increaseClickingArea(view: View, extraSpace: Int) {
    val parent = view.parent as View
    parent.post {
        val touchableArea = Rect()
        view.getHitRect(touchableArea)
        touchableArea.top -= extraSpace.px
        touchableArea.bottom += extraSpace.px
        touchableArea.left -= extraSpace.px
        touchableArea.right += extraSpace.px
        parent.touchDelegate = TouchDelegate(touchableArea, view)
    }
}