package com.leaguechampions.ui.championdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.data.local.Const;
import com.leaguechampions.databinding.ActivityChampionDetailsBinding;
import com.leaguechampions.injection.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ChampionDetailsActivity extends DaggerAppCompatActivity {

    @Inject
    protected ViewModelFactory viewModelFactory;

    private ActivityChampionDetailsBinding binding;
    private ChampionDetailsViewModel viewModel;

    public static Intent getIntent(Context context, String championId) {
        Intent intent = new Intent(context, ChampionDetailsActivity.class);
        intent.putExtra(Const.KEY_CHAMPION_ID, championId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_champion_details);

        setSupportActionBar(binding.activityChampionDetailsToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        String championId = getIntent().getStringExtra(Const.KEY_CHAMPION_ID);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionDetailsViewModel.class);
        viewModel.setChampionId(championId);
        viewModel.getRiotResponse().observe(this, championResource ->
                // TODO: Error handling
                binding.setChampion(championResource.data)
        );
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

//    @Override
//    public void showError(@StringRes int stringId) {
//        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show();
//    }

//    @Override
//    public void showError(@StringRes int stringId, int errorCode) {
//        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show();
//    }
}
