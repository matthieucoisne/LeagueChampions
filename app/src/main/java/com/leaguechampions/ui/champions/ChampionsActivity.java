package com.leaguechampions.ui.champions;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.databinding.ActivityChampionsBinding;
import com.leaguechampions.injection.viewmodel.ViewModelFactory;
import com.leaguechampions.ui.championdetails.ChampionDetailsActivity;
import com.leaguechampions.ui.settings.SettingsActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ChampionsActivity extends DaggerAppCompatActivity {

    @Inject
    protected ViewModelFactory viewModelFactory;

    private ActivityChampionsBinding binding;
    private ChampionsAdapter adapter;
    private ChampionsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_champions);

        setSupportActionBar(binding.activityChampionsToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionsViewModel.class);
        viewModel.getRiotResponse().observe(this, riotResponseResource ->
                setAdapter(riotResponseResource.data)
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                showSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setAdapter(RiotResponse riotResponse) {
        adapter = new ChampionsAdapter(riotResponse, champion ->
                showDetails(champion.getId())
        );
        binding.activityChampionsRvChampions.setAdapter(adapter);
    }

//    @Override
//    public void showError(@StringRes int stringId) {
//        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show();
//    }

//    @Override
//    public void showError(@StringRes int stringId, int errorCode) {
//        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show();
//    }

    private void showDetails(String championId) {
        startActivity(ChampionDetailsActivity.getIntent(this, championId));
    }

    private void showSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
