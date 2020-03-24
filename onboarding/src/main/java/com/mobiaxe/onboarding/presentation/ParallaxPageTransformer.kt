package com.mobiaxe.onboarding.presentation

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ParallaxPageTransformer : ViewPager2.PageTransformer {

    private val mViewsToParallax = mutableListOf<ParallaxTransformInformation>()

    companion object {
        const val PARALLAX_EFFECT_DEFAULT = -101.1986f
    }

    fun addViewToParallax(viewInfo: ParallaxTransformInformation): ParallaxPageTransformer {
        mViewsToParallax.add(viewInfo)
        return this
    }

    override fun transformPage(page: View, position: Float) {
        val pageWidth: Int = page.width

        if (position < -1) {
            page.alpha = 1f
        } else if (position <= 1 && mViewsToParallax.isNotEmpty()) {
            for (parallaxTransformInformation in mViewsToParallax) {
                applyParallaxEffect(
                    page, position, pageWidth, parallaxTransformInformation,
                    position > 0
                )
            }
        } else {
            page.alpha = 1f
        }
    }

    private fun applyParallaxEffect(
        page: View,
        position: Float,
        pageWidth: Int,
        information: ParallaxTransformInformation,
        isEnter: Boolean
    ) {
        if (information.isValid() && page.findViewById<View>(information.resource) != null) {
            if (isEnter && information.isEnterDefault().not()) {
                page.findViewById<View>(information.resource).translationX = -position * (pageWidth / information.parallaxEnterEffect)
            } else if (isEnter.not() && information.isExitDefault().not()) {
                page.findViewById<View>(information.resource).translationX = -position * (pageWidth / information.parallaxExitEffect)
            }
        }
    }

    inner class ParallaxTransformInformation(
        val resource: Int,
        val parallaxEnterEffect: Float,
        val parallaxExitEffect: Float
    ) {

        fun isValid() = parallaxEnterEffect != 0f && parallaxExitEffect != 0f && resource != -1

        fun isEnterDefault() = parallaxEnterEffect == PARALLAX_EFFECT_DEFAULT

        fun isExitDefault() = parallaxExitEffect == PARALLAX_EFFECT_DEFAULT
    }
}