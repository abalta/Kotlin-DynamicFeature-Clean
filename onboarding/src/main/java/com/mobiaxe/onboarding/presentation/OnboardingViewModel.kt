package com.mobiaxe.onboarding.presentation

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiaxe.core.presentation.BaseViewModel
import com.mobiaxe.onboarding.R
import com.mobiaxe.onboarding.data.OnboardingData
import com.mobiaxe.onboarding.domain.usecase.SavePlatform
import com.mobiaxe.wasd.splash.domain.usecase.OnboardingPassed

class OnboardingViewModel(
    private val appContext: Application,
    private val onboardingPassed: OnboardingPassed,
    private val savePlatform: SavePlatform
): BaseViewModel(savePlatform)  {

    private val _passOnboardingLiveData = MutableLiveData<Boolean>()
    val passOnboardingLiveData: LiveData<Boolean> = _passOnboardingLiveData

    val lastPageObservable = ObservableBoolean(false)
    val checkWindowsObsv = ObservableBoolean(false)
    val checkPlaystationObsv = ObservableBoolean(false)
    val checkXboxObsv = ObservableBoolean(false)
    val checkNintendoObsv = ObservableBoolean(false)

    private val selectedPlatforms = mutableSetOf<Int>()

    companion object {
        private const val SWITCH = 130
        private const val PC = 6
        private const val XBOX = 49
        private const val PS4 = 48
    }

    fun isOnboardingPassed() {
        _passOnboardingLiveData.value =  onboardingPassed.execute(true)
    }

    fun getOnboardingData(): List<OnboardingData> {
        val titleList = appContext.resources.getStringArray(R.array.title_onboarding)
        val descriptionList = appContext.resources.getStringArray(R.array.description_onboarding)
        val drawableList = mutableListOf(
            R.drawable.witcher_onboarding_one,
            R.drawable.godofwar_onboarding_two,
            R.drawable.rdr_onboarding_three)

        return (titleList.indices).map { i ->
            OnboardingData(titleList[i], descriptionList[i], drawableList[i], i)
        }
    }

    fun selectWindows() {
        if (!checkWindowsObsv.get()) {
            checkWindowsObsv.set(true)
            selectedPlatforms.add(PC)
        } else {
            checkWindowsObsv.set(false)
            selectedPlatforms.remove(PC)
        }

    }

    fun selectPlaystation() {
        if (!checkPlaystationObsv.get()) {
            checkPlaystationObsv.set(true)
            selectedPlatforms.add(PS4)
        } else {
            checkPlaystationObsv.set(false)
            selectedPlatforms.remove(PS4)
        }
    }

    fun selectXBox() {
        if (!checkXboxObsv.get()) {
            checkXboxObsv.set(true)
            selectedPlatforms.add(XBOX)
        } else {
            checkXboxObsv.set(false)
            selectedPlatforms.remove(XBOX)
        }
    }

    fun selectNintendo() {
        if (!checkNintendoObsv.get()) {
            checkNintendoObsv.set(true)
            selectedPlatforms.add(SWITCH)
        } else {
            checkNintendoObsv.set(false)
            selectedPlatforms.remove(SWITCH)
        }
    }

    fun savePlatform() {
        savePlatform.execute(selectedPlatforms.joinToString(prefix = "(", postfix = ")")) {
            isOnboardingPassed()
        }
    }
}