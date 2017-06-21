package com.leaguechampions.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.leaguechampions.core.Const;

public class PrefUtils {

    private PrefUtils() {}

    public static boolean isMockMode(Context context) {
        return isMockMode(PreferenceManager.getDefaultSharedPreferences(context));
    }

    public static boolean isMockMode(SharedPreferences sharedPreferences) {
        return sharedPreferences
                .getBoolean(Const.PREF_KEY_MOCK_MODE, false);
    }

    public static void setMockMode(Context context, boolean mockMode) {
        setMockMode(PreferenceManager.getDefaultSharedPreferences(context), mockMode);
    }

    public static void setMockMode(SharedPreferences sharedPreferences, boolean mockMode) {
        sharedPreferences
                .edit()
                .putBoolean(Const.PREF_KEY_MOCK_MODE, mockMode)
                .apply();
    }
}
