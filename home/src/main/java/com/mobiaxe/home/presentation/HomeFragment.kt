package com.mobiaxe.home.presentation

import android.os.Bundle
import android.view.View
import com.mobiaxe.core.extension.observe
import com.mobiaxe.core.presentation.BaseFragment
import com.mobiaxe.home.R
import com.mobiaxe.home.databinding.FragmentHomeBinding
import com.mobiaxe.home.koin.loadModules
import com.mobiaxe.home.koin.unloadModules
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private val sectionListDataAdapter: SectionListDataAdapter by inject()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getViewModel(): Lazy<HomeViewModel> = viewModel()

    override fun bindViewModel(dataBinding: FragmentHomeBinding) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
        vModel().getDashboard()
    }

    private fun initListeners() {
        getBinding().recyclerHome.adapter = sectionListDataAdapter
    }

    private fun initObservers() {
        vModel().apply {
            observe(sectionListLiveData) { sectionDataList ->
                sectionListDataAdapter.submitList(sectionDataList)
                observe(popularGamesPagedList) {
                    sectionDataList[0].gameListAdapter?.submitList(it)
                }
                observe(networkState) {
                    sectionDataList[0].gameListAdapter?.setNetworkState(it)
                }
            }
        }
    }

    override fun loadKoinModules() {
        loadModules()
    }

    override fun unloadKoinModules() {
        unloadModules()
    }
}