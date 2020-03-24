package com.mobiaxe.core.extension

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.start(intent: Intent?) = intent?.let { startActivity(it) }