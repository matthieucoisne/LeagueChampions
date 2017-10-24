package com.leaguechampions.ui.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leaguechampions.LeagueChampions;
import com.leaguechampions.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class SettingsActivity extends AppCompatActivity implements SettingsPresenter.SettingsView {

    @BindView(R.id.activity_settings_tvVersion)
    protected TextView tvVersion;

    @BindView(R.id.activity_settings_llyMockMode)
    protected LinearLayout llyMockMode;

    @BindView(R.id.activity_settings_switchMockMode)
    protected SwitchCompat switchMockMode;

    @Inject
    protected SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        Toolbar toolbar = ButterKnife.findById(this, R.id.activity_settings_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.settings);
        }

        DaggerSettingsComponent
                .builder()
                .appComponent((((LeagueChampions) getApplicationContext()).getAppComponent()))
                .settingsPresenterModule(new SettingsPresenterModule(this))
                .build()
                .inject(this);

        presenter.onActivityCreated(savedInstanceState, getIntent().getExtras());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @OnCheckedChanged(R.id.activity_settings_switchMockMode)
    public void onMockModeCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        presenter.onMockModeCheckedChanged(isChecked);
    }

    @Override
    public void setVersion(String version) {
        tvVersion.setText(String.format(getString(R.string.version), version));
    }

    @Override
    public void showMockMode(boolean mockMock) {
        llyMockMode.setVisibility(View.VISIBLE);
        switchMockMode.setChecked(mockMock);
    }

    @Override
    public void doFinish() {
        finish();
    }
}
