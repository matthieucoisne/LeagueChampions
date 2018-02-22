package com.leaguechampions.ui.champions;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.injection.viewmodel.ViewModelFactory;
import com.leaguechampions.ui.championdetails.ChampionDetailsActivity;
import com.leaguechampions.ui.settings.SettingsActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ChampionsActivity extends DaggerAppCompatActivity {

    @BindView(R.id.activity_champions_rvChampions)
    protected RecyclerView rvChampions;

    @Inject
    protected ViewModelFactory viewModelFactory;

    @Inject
    protected Picasso picasso;

    private ChampionsViewModel viewModel;
    private ChampionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.activity_champions_toolbar);
        setSupportActionBar(toolbar);
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
        adapter = new ChampionsAdapter(riotResponse, picasso, (version, champion) ->
                showDetails(version, champion.getId())
        );
        rvChampions.setAdapter(adapter);
        rvChampions.setLayoutManager(new GridLayoutManager(this, 3));
    }

//    @Override
//    public void showError(@StringRes int stringId) {
//        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show();
//    }

//    @Override
//    public void showError(@StringRes int stringId, int errorCode) {
//        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show();
//    }

    private void showDetails(String version, String championId) {
        startActivity(ChampionDetailsActivity.getIntent(this, version, championId));
    }

    private void showSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
