package com.leaguechampions.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.leaguechampions.data.local.Const

object PrefUtils {

    fun isMockMode(context: Context): Boolean {
        return isMockMode(PreferenceManager.getDefaultSharedPreferences(context))
    }

    fun isMockMode(sharedPreferences: SharedPreferences): Boolean {
        return sharedPreferences.getBoolean(Const.PREF_KEY_MOCK_MODE,false)
    }

    fun setMockMode(context: Context, mockMode: Boolean) {
        setMockMode(PreferenceManager.getDefaultSharedPreferences(context), mockMode)
    }

    fun setMockMode(sharedPreferences: SharedPreferences, mockMode: Boolean) {
        sharedPreferences
                .edit()
                .putBoolean(Const.PREF_KEY_MOCK_MODE, mockMode)
                .apply()
    }
}
