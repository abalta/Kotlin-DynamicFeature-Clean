package com.mobiaxe.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<V: ViewModel, B: ViewDataBinding>: AppCompatActivity() {

    private lateinit var viewModel: V
    private lateinit var dataBinding: B

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

    fun getBinding(): B = dataBinding
    fun vm() = getViewModel().value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        loadKoinModules()
        viewModel = getViewModel().value
        bindViewModel(dataBinding)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules()
    }

}