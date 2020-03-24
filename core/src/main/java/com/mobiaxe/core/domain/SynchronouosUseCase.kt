package com.mobiaxe.core.domain

interface SynchronouosUseCase<out Results, in Params> {
    fun execute(params: Params): Results
}