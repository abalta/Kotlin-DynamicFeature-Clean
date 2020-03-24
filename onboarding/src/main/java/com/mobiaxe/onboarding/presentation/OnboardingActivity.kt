package com.mobiaxe.onboarding.presentation

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mobiaxe.core.presentation.BaseActivity
import com.mobiaxe.onboarding.R
import com.mobiaxe.onboarding.databinding.ActivityOnboardingBinding
import com.mobiaxe.onboarding.koin.loadModules
import com.mobiaxe.onboarding.koin.unloadModules
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class OnboardingActivity : BaseActivity<OnboardingViewModel, ActivityOnboardingBinding>() {

    companion object {
        private const val IMAGE_PARALLAX = 2f
        private const val TITLE_PARALLAX_ENTER = -1.95f
        private const val TITLE_PARALLAX_EXIT = 0.5f
    }

    private val onboardingAdapter: OnboardingAdapter by inject { parametersOf(this) }

    private val mPageTransformer: ParallaxPageTransformer by inject()

    private val viewPage2Callback: ViewPager2PageChangeCallback by inject { parametersOf(this) }

    private val tabIconArray = arrayOf(
        R.drawable.tab_page_one_selector,
        R.drawable.tab_page_two_selector,
        R.drawable.tab_page_three_selector,
        R.drawable.tab_page_four_selector
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragments()
        setUpParallaxEffect()
        setUpTabIndicator()
        initListener()
    }

    override fun getLayoutId(): Int = R.layout.activity_onboarding

    override fun getViewModel(): Lazy<OnboardingViewModel> = viewModel()

    override fun bindViewModel(dataBinding: ActivityOnboardingBinding) {

    }

    override fun onResume() {
        super.onResume()
        runBasicImmersive()
    }

    private fun runBasicImmersive() {
        val uiOptions: Int = window.decorView.systemUiVisibility
        var newUiOptions = uiOptions
        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN
        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = newUiOptions
    }

    private fun addFragments() {
        getViewModel().value.getOnboardingData().map {
            onboardingAdapter.addFragment(OnboardingFragment.newInstance(it))
        }
        onboardingAdapter.addFragment(SelectPlatformFragment.newInstance())
    }

    private fun setUpParallaxEffect() {
        getBinding().apply {
            pagerOnboarding.adapter = onboardingAdapter
            pagerOnboarding.registerOnPageChangeCallback(viewPage2Callback)

            val transformationImage = ParallaxPageTransformer().ParallaxTransformInformation(
                R.id.img_onboarding,
                IMAGE_PARALLAX,
                IMAGE_PARALLAX
            )
            mPageTransformer.addViewToParallax(transformationImage)

            val transformationTitle = ParallaxPageTransformer().ParallaxTransformInformation(
                R.id.title_onboarding,
                TITLE_PARALLAX_ENTER,
                TITLE_PARALLAX_EXIT
            )
            mPageTransformer.addViewToParallax(transformationTitle)

            val transformationDescription = ParallaxPageTransformer().ParallaxTransformInformation(
                R.id.description_onboarding,
                TITLE_PARALLAX_ENTER,
                TITLE_PARALLAX_EXIT
            )
            mPageTransformer.addViewToParallax(transformationDescription)

            pagerOnboarding.setPageTransformer(mPageTransformer)

            TabLayoutMediator(pagerIndicator, pagerOnboarding) { _, _ ->
            }.attach()
        }
    }

    private fun setUpTabIndicator() {
        getBinding().pagerIndicator.apply {
            onboardingAdapter.getFragmentList().forEachIndexed { i, _ ->
                this.getTabAt(i)?.setIcon(tabIconArray[i])
            }
        }
    }

    private fun initListener() {
        getBinding().apply {
            pagerRightBtn.setOnClickListener {
                if (!vm().lastPageObservable.get())
                    pagerOnboarding.setCurrentItem(pagerOnboarding.currentItem + 1, true)
            }

            pagerLeftBtn.setOnClickListener {
                if (!vm().lastPageObservable.get())
                    pagerOnboarding.setCurrentItem(pagerOnboarding.currentItem - 1, true)
            }
        }
    }

    override fun loadKoinModules() {
        loadModules()
    }

    override fun unloadKoinModules() {
        unloadModules()
    }

    override fun onDestroy() {
        super.onDestroy()
        getBinding().pagerOnboarding.unregisterOnPageChangeCallback(viewPage2Callback)
    }

    inner class ViewPager2PageChangeCallback : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            if (onboardingAdapter.getFragmentList().size > 1) {
                val newProgress =
                    (position + positionOffset) / (onboardingAdapter.getFragmentList().size - 1)
                getBinding().onboardingRoot.progress = newProgress
            }
        }

        override fun onPageSelected(position: Int) {
            vm().lastPageObservable.set(onboardingAdapter.getFragmentList().size.minus(1) == position)
        }
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }
}
