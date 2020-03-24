package com.mobiaxe.core.presentation

import androidx.lifecycle.ViewModel
import com.mobiaxe.core.domain.SuspendUseCase

abstract class BaseViewModel(vararg suspendUseCase: SuspendUseCase<*, *>): ViewModel() {

    protected var useCaseList: MutableList<SuspendUseCase<*, *>> = mutableListOf()

    init {
        useCaseList.addAll(suspendUseCase)
    }

    override fun onCleared() {
        super.onCleared()
        useCaseList.forEach {
            it.cancel()
        }
    }
}