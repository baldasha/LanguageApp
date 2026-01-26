package com.example.languageapp.core.data

import android.content.SharedPreferences
import androidx.core.content.edit

interface OnBoardingPref {
    fun getStatus() : Boolean
    fun saveStatus(status : Boolean)
}

class OnBoardingSharedPref(
    private val sharedPreferences: SharedPreferences,
) : OnBoardingPref{
    override fun getStatus(): Boolean {
        return sharedPreferences.getBoolean("status", false)
    }

    override fun saveStatus(status: Boolean) {
        sharedPreferences.edit{
            putBoolean("status", status)
        }
    }
}