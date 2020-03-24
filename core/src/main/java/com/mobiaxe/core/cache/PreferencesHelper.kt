package com.mobiaxe.core.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
open class PreferencesHelper(context: Context) {

    companion object {
        private const val PREF_WASD_PACKAGE_NAME = "com.mobiaxe.wasd"

        private const val PREF_KEY_LAST_CACHE = "last_cache"

        private const val PREF_KEY_ONBOARDING_SHOW = "onboarding_show"
    }

    private val wasdPref: SharedPreferences

    init {
        wasdPref = context.getSharedPreferences(PREF_WASD_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = wasdPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = wasdPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

    var onboardingShow: Boolean
        get() = wasdPref.getBoolean(PREF_KEY_ONBOARDING_SHOW, false)
        set(isOnboardingShown) = wasdPref.edit().putBoolean(PREF_KEY_ONBOARDING_SHOW, isOnboardingShown).apply()

}
