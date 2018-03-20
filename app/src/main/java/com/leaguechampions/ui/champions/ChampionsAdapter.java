package com.leaguechampions.ui.champions;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.leaguechampions.utils.UrlUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ViewHolder> {

    private final List<Champion> data;
    private final Picasso picasso;
    private final onItemClickListener listener;

    ChampionsAdapter(List<Champion> champions, Picasso picasso, onItemClickListener listener) {
        this.data = champions;
        this.picasso = picasso;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_champions_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Context context = holder.itemView.getContext();
        Champion champion = data.get(position);
        holder.tvChampion.setText(champion.getName());

        if (Const.isGlide) {
            Glide.with(context).load(UrlUtils.getImageUrl(context, champion)).into(holder.ivChampion);
        } else {
            picasso.load(UrlUtils.getImageUrl(context, champion)).into(holder.ivChampion);
        }

        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                listener.onItemClick(data.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.activity_champions_row_ivChampion)
        ImageView ivChampion;

        @BindView(R.id.activity_champions_row_tvChampion)
        TextView tvChampion;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onItemClickListener {
        void onItemClick(Champion champion);
    }
}
