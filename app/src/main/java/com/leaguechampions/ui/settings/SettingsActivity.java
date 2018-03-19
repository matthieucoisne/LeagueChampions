package com.leaguechampions.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leaguechampions.BuildConfig;
import com.leaguechampions.R;
import com.leaguechampions.utils.PrefUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import dagger.android.support.DaggerAppCompatActivity;

public class SettingsActivity extends DaggerAppCompatActivity {

    @BindView(R.id.activity_settings_tvVersion)
    protected TextView tvVersion;

    @BindView(R.id.activity_settings_llyDeveloperOptions)
    protected LinearLayout llyDeveloperOptions;

    @BindView(R.id.activity_settings_switchMockMode)
    protected SwitchCompat switchMockMode;

    @Inject
    protected SharedPreferences preferences;

    // Workaround for BuildConfig.BUILD_TYPE in methods for Unit Tests
    private String buildType = BuildConfig.BUILD_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.activity_settings_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.settings);
        }

        tvVersion.setText(String.format(getString(R.string.version), BuildConfig.VERSION_NAME));

        if ("debug".equals(buildType)) {
            llyDeveloperOptions.setVisibility(View.VISIBLE);
            switchMockMode.setChecked(PrefUtils.isMockMode(preferences));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnCheckedChanged(R.id.activity_settings_switchMockMode)
    public void onMockModeCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PrefUtils.setMockMode(preferences, isChecked);
    }
}
