package com.leaguechampions.features.champions.presentation.champions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leaguechampions.features.champions.databinding.ActivityChampionsItemBinding
import com.leaguechampions.libraries.core.utils.loadChampionImage

class ChampionsAdapter(
    private val listener: (ChampionUiModel) -> Unit
) : RecyclerView.Adapter<ChampionsAdapter.ViewHolder>() {

    private var data = ChampionsUiModel(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityChampionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val champion = data.champions[position]
        holder.binding.tvChampionName.text = champion.name
        loadChampionImage(holder.binding.ivChampion, champion.id, champion.version)
        holder.binding.root.setOnClickListener {
            listener(champion)
        }
    }

    override fun getItemCount() = data.champions.size

    fun setData(data: ChampionsUiModel) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(
        val binding: ActivityChampionsItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
