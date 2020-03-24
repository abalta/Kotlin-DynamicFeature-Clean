package com.mobiaxe.wasd.navigation

import android.content.Intent
import com.mobiaxe.wasd.BuildConfig

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(BuildConfig.APPLICATION_ID, className)

internal fun String.loadIntentOrNull(): Intent? =
    try {
        Class.forName(this).run { intentTo(this@loadIntentOrNull) }
    } catch (e: ClassNotFoundException) {
        null
    }
