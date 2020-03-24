package com.mobiaxe.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<V : ViewModel, B : ViewDataBinding>: Fragment() {

    private lateinit var viewModel: V
    private lateinit var dataBinding: B
    private lateinit var rootView: View

    /**
     * @return layout resource id
     */
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): Lazy<V>

    /**
     * Override for bind view
     *
     * @param dataBinding instance
     */
    abstract fun bindViewModel(dataBinding: B)

    abstract fun loadKoinModules()

    abstract fun unloadKoinModules()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules()
        viewModel = getViewModel().value
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        rootView = dataBinding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel(dataBinding)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unloadKoinModules()
    }


    fun getBinding() = dataBinding

    fun vModel() = getViewModel().value


}