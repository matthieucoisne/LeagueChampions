package com.leaguechampions.ui.championdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leaguechampions.R;
import com.leaguechampions.data.local.Const;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.injection.viewmodel.ViewModelFactory;
import com.leaguechampions.utils.UrlUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ChampionDetailsActivity extends DaggerAppCompatActivity {

    @BindView(R.id.activity_champion_details_ivChampion)
    protected ImageView ivChampion;

    @BindView(R.id.activity_champion_details_tvName)
    protected TextView tvName;

    @BindView(R.id.activity_champion_details_tvLore)
    protected TextView tvLore;

    @Inject
    protected ViewModelFactory viewModelFactory;

    @Inject
    protected Picasso picasso;

    private ChampionDetailsViewModel viewModel;

    public static Intent getIntent(Context context, String version, String championId) {
        Intent intent = new Intent(context, ChampionDetailsActivity.class);
        intent.putExtra(Const.KEY_VERSION, version);
        intent.putExtra(Const.KEY_CHAMPION_ID, championId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_details);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.activity_champion_details_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        String championId = getIntent().getStringExtra(Const.KEY_CHAMPION_ID);
        String version = getIntent().getStringExtra(Const.KEY_VERSION);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionDetailsViewModel.class);
        viewModel.setChampionId(championId);
        viewModel.getRiotResponse().observe(this, riotResponseResource ->
                showDetails(version, riotResponseResource.data.getData().get(championId))
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

    private void showDetails(String version, Champion champion) {
        if (Const.isGlide) {
            Glide.with(this).load(UrlUtils.getImageUrl(this, version, champion.getId())).into(ivChampion);
        } else {
            picasso.load(UrlUtils.getImageUrl(this, version, champion.getId())).into(ivChampion);
        }
        tvName.setText(champion.getName());
        tvLore.setText(Html.fromHtml(champion.getLore()));
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
