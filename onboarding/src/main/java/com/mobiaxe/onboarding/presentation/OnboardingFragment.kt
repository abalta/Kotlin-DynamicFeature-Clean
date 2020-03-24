package com.mobiaxe.onboarding.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobiaxe.core.presentation.BaseFragment
import com.mobiaxe.onboarding.R
import com.mobiaxe.onboarding.data.OnboardingData
import com.mobiaxe.onboarding.databinding.FragmentOnboardingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingFragment : BaseFragment<OnboardingViewModel, FragmentOnboardingBinding>() {

    private lateinit var onboardingData: OnboardingData

    companion object {

        private const val DATA = "data"

        fun newInstance(data: OnboardingData): OnboardingFragment {
            val fragment = OnboardingFragment()
            val b = Bundle()
            b.putParcelable(DATA, data)
            fragment.arguments = b
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_onboarding

    override fun getViewModel(): Lazy<OnboardingViewModel> = viewModel()

    override fun bindViewModel(dataBinding: FragmentOnboardingBinding) {
        dataBinding.onboardingData = onboardingData
        dataBinding.executePendingBindings()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        onboardingData = arguments?.get(DATA) as OnboardingData
        rootView?.tag = onboardingData.position
        return rootView
    }

    override fun loadKoinModules() {
    }

    override fun unloadKoinModules() {
    }
}