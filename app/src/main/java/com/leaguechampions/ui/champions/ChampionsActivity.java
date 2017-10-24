package com.leaguechampions.ui.champions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.leaguechampions.R;
import com.leaguechampions.LeagueChampions;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.ui.championdetails.ChampionDetailsActivity;
import com.leaguechampions.ui.settings.SettingsActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionsActivity extends AppCompatActivity implements ChampionsPresenter.ChampionsView {

    @BindView(R.id.activity_champions_rvChampions)
    protected RecyclerView rvChampions;

    @Inject
    protected ChampionsPresenter presenter;

    @Inject
    protected Picasso picasso;

    private ChampionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        ButterKnife.bind(this);

        Toolbar toolbar = ButterKnife.findById(this, R.id.activity_champions_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        DaggerChampionsComponent
                .builder()
                .appComponent((((LeagueChampions) getApplicationContext()).getAppComponent()))
                .championsPresenterModule(new ChampionsPresenterModule(this))
                .build()
                .inject(this);

        presenter.onActivityCreated(savedInstanceState, getIntent().getExtras());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void showSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void setAdapter(RiotResponse riotResponse) {
        adapter = new ChampionsAdapter(riotResponse, picasso, new ChampionsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(String version, Champion champion) {
                startActivity(ChampionDetailsActivity.getIntent(ChampionsActivity.this, version, champion.getId()));
            }
        });
        rvChampions.setAdapter(adapter);
        rvChampions.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void showError(@StringRes int stringId) {
        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(@StringRes int stringId, int errorCode) {
        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show();
    }
}
