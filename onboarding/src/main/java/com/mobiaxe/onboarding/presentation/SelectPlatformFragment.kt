package com.mobiaxe.onboarding.presentation

import android.os.Bundle
import android.view.View
import com.mobiaxe.core.extension.observe
import com.mobiaxe.core.extension.start
import com.mobiaxe.core.presentation.BaseFragment
import com.mobiaxe.onboarding.R
import com.mobiaxe.onboarding.databinding.FragmentPlatformBinding
import com.mobiaxe.wasd.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectPlatformFragment : BaseFragment<OnboardingViewModel, FragmentPlatformBinding>() {

    companion object {

        fun newInstance(): SelectPlatformFragment {
            val fragment = SelectPlatformFragment()
            val b = Bundle()
            fragment.arguments = b
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_platform

    override fun getViewModel(): Lazy<OnboardingViewModel> = viewModel()

    override fun bindViewModel(dataBinding: FragmentPlatformBinding) {
        dataBinding.viewModel = vModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        with(vModel()) {
            observe(passOnboardingLiveData) {
                requireActivity().start(Navigation.navigateOnboardingOrDashboard(it))
                requireActivity().finish()
            }
        }
    }


    private fun initListeners() {
        getBinding().apply {
            btnWindows.setOnClickListener {
                vModel().selectWindows()
            }

            btnNintendo.setOnClickListener {
                vModel().selectNintendo()
            }

            btnPlaystation.setOnClickListener {
                vModel().selectPlaystation()
            }

            btnXbox.setOnClickListener {
                vModel().selectXBox()
            }

            btnContinue.setOnClickListener {
                vModel().savePlatform()
            }
        }
    }

    override fun loadKoinModules() {
    }

    override fun unloadKoinModules() {
    }
}