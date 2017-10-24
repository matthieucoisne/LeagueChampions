package com.leaguechampions.ui.champions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leaguechampions.R;
import com.leaguechampions.data.local.Const;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.utils.UrlUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ViewHolder> {

    private final onItemClickListener listener;
    private final String version;
    private final Picasso picasso;
    private final List<Champion> data;

    public ChampionsAdapter(RiotResponse riotResponse, Picasso picasso, onItemClickListener listener) {
        this.data = new ArrayList<>(riotResponse.getData().values());
        Collections.sort(data);
        this.picasso = picasso;
        this.version = riotResponse.getVersion();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_champions_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Context context = holder.itemView.getContext();
        Champion champion = data.get(position);
        holder.tvChampion.setText(champion.getName());

        if (Const.isGlide) {
            Glide.with(context).load(UrlUtils.getImageUrl(context, version, champion.getId())).into(holder.ivChampion);
        } else {
            picasso.load(UrlUtils.getImageUrl(context, version, champion.getId())).into(holder.ivChampion);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(version, data.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.activity_champions_row_ivChampion)
        protected ImageView ivChampion;

        @BindView(R.id.activity_champions_row_tvChampion)
        protected TextView tvChampion;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onItemClickListener {
        void onItemClick(String version, Champion champion);
    }
}
