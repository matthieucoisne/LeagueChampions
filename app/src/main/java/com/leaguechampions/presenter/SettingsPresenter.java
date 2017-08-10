package com.leaguechampions.presenter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.leaguechampions.BuildConfig;
import com.leaguechampions.utils.PrefUtils;

import javax.inject.Inject;

public class SettingsPresenter {

    private final SharedPreferences preferences;
    private SettingsViewable viewable;

    // Workaround for BuildConfig.BUILD_TYPE in methods for Unit Tests
    private String buildType = BuildConfig.BUILD_TYPE;

    public interface SettingsViewable {
        void setVersion(String version);
        void showMockMode(boolean mockMock);
        void doFinish();
    }

    @Inject
    SettingsPresenter(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void setViewable(SettingsViewable viewable) {
        this.viewable = viewable;
    }

    public void onActivityCreated(Bundle savedInstanceState, Bundle arguments) {
        viewable.setVersion(BuildConfig.VERSION_NAME);
        if ("debug".equals(buildType)) {
            viewable.showMockMode(PrefUtils.isMockMode(preferences));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                viewable.doFinish();
                return true;
            default:
                return false;
        }
    }

    public void onMockModeCheckedChanged(boolean isChecked) {
        PrefUtils.setMockMode(preferences, isChecked);
    }
}
