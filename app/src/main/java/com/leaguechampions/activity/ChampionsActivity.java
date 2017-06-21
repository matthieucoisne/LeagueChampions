package com.leaguechampions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.leaguechampions.R;
import com.leaguechampions.adapter.ChampionsAdapter;
import com.leaguechampions.core.LeagueChampions;
import com.leaguechampions.model.Champion;
import com.leaguechampions.model.Champions;
import com.leaguechampions.presenter.ChampionsPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionsActivity extends AppCompatActivity implements ChampionsPresenter.ChampionsViewable {

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
        ((LeagueChampions) getApplicationContext()).getAppComponent().inject(this);

        Toolbar toolbar = ButterKnife.findById(this, R.id.activity_champions_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        presenter.setViewable(this);
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
    public void setAdapter(Champions champions) {
        adapter = new ChampionsAdapter(champions, picasso, new ChampionsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(String version, Champion champion) {
                startActivity(ChampionDetailsActivity.getIntent(ChampionsActivity.this, version, champion.getId()));
            }
        });
        rvChampions.setAdapter(adapter);
        rvChampions.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
