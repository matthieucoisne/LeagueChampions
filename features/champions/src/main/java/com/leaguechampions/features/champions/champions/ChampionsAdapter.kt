package com.leaguechampions.features.champions.champions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leaguechampions.features.champions.databinding.ActivityChampionsItemBinding

class ChampionsAdapter(
        private val data: ChampionsUiModel,
        private val listener: (ChampionUiModel) -> Unit
) : RecyclerView.Adapter<ChampionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityChampionsItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val champion = data.champions[position]
        holder.binding.tvChampionName.text = champion.name
//        loadChampionImage(holder.binding.ivChampion, champion)
        holder.binding.root.setOnClickListener {
            listener(champion)
        }
    }

    override fun getItemCount() = data.champions.size

    class ViewHolder(
            val binding: ActivityChampionsItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
