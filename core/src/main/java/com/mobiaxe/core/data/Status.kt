package com.mobiaxe.core.data

import android.view.View

enum class Status(val visibility: Int) {
    SUCCESS(View.GONE),
    ERROR(View.GONE),
    LOADING(View.VISIBLE),
    EMPTY(View.GONE)
}
