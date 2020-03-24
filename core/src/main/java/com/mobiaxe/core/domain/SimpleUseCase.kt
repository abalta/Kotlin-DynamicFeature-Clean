package com.mobiaxe.core.domain

abstract class SimpleUseCase<Type, in Params> where Type : Any {
    abstract fun run(params: Params): Type
    operator fun invoke(params: Params): Type = run(params)
}