package com.leaguechampions.ui.champions;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leaguechampions.R;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.databinding.ActivityChampionsItemBinding;

import java.util.List;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ViewHolder> {

    private final List<Champion> champions;
    private final onItemClickListener listener;

    public ChampionsAdapter(List<Champion> champions, onItemClickListener listener) {
        this.champions = champions;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityChampionsItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.activity_champions_item,
                parent,
                false
        );
        binding.setOnItemClickListener(listener);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setChampion(champions.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return champions == null ? 0 : champions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ActivityChampionsItemBinding binding;

        ViewHolder(ActivityChampionsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onItemClickListener {
        void onItemClick(Champion champion);
    }
}
