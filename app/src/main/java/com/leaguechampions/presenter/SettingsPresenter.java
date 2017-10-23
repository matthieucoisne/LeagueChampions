package com.leaguechampions.presenter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.leaguechampions.BuildConfig;
import com.leaguechampions.utils.PrefUtils;

import javax.inject.Inject;

public class SettingsPresenter {

    private final SharedPreferences preferences;
    private final SettingsView view;

    // Workaround for BuildConfig.BUILD_TYPE in methods for Unit Tests
    private String buildType = BuildConfig.BUILD_TYPE;

    public interface SettingsView {
        void setVersion(String version);
        void showMockMode(boolean mockMock);
        void doFinish();
    }

    @Inject
    SettingsPresenter(SettingsView view, SharedPreferences preferences) {
        this.view = view;
        this.preferences = preferences;
    }

    public void onActivityCreated(Bundle savedInstanceState, Bundle arguments) {
        view.setVersion(BuildConfig.VERSION_NAME);
        if ("debug".equals(buildType)) {
            view.showMockMode(PrefUtils.isMockMode(preferences));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                view.doFinish();
                return true;
            default:
                return false;
        }
    }

    public void onMockModeCheckedChanged(boolean isChecked) {
        PrefUtils.setMockMode(preferences, isChecked);
    }
}
