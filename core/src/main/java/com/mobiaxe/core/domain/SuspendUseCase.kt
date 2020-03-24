package com.mobiaxe.core.domain

import kotlinx.coroutines.*
import timber.log.Timber

abstract class SuspendUseCase<Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type
    suspend operator fun invoke(params: Params): Type = run(params)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleErrors(throwable)
    }

    private fun handleErrors(error: Throwable?) {
        Timber.e(error, "Error")
    }

    fun execute(params: Params, onResult: (Type) -> Unit = {}) {
        viewModelScope.launch (exceptionHandler) {
            val result = withContext(Dispatchers.IO) {
                run(params)
            }
            onResult(result)
        }
    }

    fun cancel() {
        viewModelScope.coroutineContext.cancelChildren()
    }
}