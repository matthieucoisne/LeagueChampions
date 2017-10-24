package com.leaguechampions.ui.championdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leaguechampions.R;
import com.leaguechampions.data.local.Const;
import com.leaguechampions.LeagueChampions;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.utils.UrlUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionDetailsActivity extends AppCompatActivity implements ChampionDetailsPresenter.ChampionDetailsView {

    @BindView(R.id.activity_champion_details_ivChampion)
    protected ImageView ivChampion;

    @BindView(R.id.activity_champion_details_tvName)
    protected TextView tvName;

    @BindView(R.id.activity_champion_details_tvLore)
    protected TextView tvLore;

    @Inject
    protected ChampionDetailsPresenter presenter;

    @Inject
    protected Picasso picasso;

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

        Toolbar toolbar = ButterKnife.findById(this, R.id.activity_champion_details_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        DaggerChampionDetailsComponent
                .builder()
                .appComponent((((LeagueChampions) getApplicationContext()).getAppComponent()))
                .championDetailsPresenterModule(new ChampionDetailsPresenterModule(this))
                .build()
                .inject(this);

        presenter.onActivityCreated(savedInstanceState, getIntent().getExtras());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void showDetails(String version, Champion champion) {
        if (Const.isGlide) {
            Glide.with(this).load(UrlUtils.getImageUrl(this, version, champion.getId())).into(ivChampion);
        } else {
            picasso.load(UrlUtils.getImageUrl(this, version, champion.getId())).into(ivChampion);
        }
        tvName.setText(champion.getName());
        tvLore.setText(Html.fromHtml(champion.getLore()));
    }

    @Override
    public void showError(@StringRes int stringId) {
        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(@StringRes int stringId, int errorCode) {
        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doFinish() {
        finish();
    }
}
