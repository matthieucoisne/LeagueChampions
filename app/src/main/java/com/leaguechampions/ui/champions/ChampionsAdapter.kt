package com.leaguechampions.ui.champions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.databinding.ActivityChampionsItemBinding

class ChampionsAdapter(
        private val data: List<Champion>,
        private val listener: (Champion) -> Unit
) : RecyclerView.Adapter<ChampionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ActivityChampionsItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.activity_champions_item,
                parent,
                false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val champion = data[position]
        holder.binding.champion = champion
        holder.binding.root.setOnClickListener { listener(champion) }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(
            val binding: ActivityChampionsItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
