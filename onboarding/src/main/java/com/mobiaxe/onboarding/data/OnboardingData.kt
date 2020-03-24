package com.mobiaxe.onboarding.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnboardingData(
    val title: String,
    val description: String,
    val background: Int,
    val position: Int
) : Parcelable