package com.leaguechampions.ui.champions

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.databinding.ActivityChampionsItemBinding

class ChampionsAdapter(private val champions: List<Champion>,
//                       private val onItemClickListener: OnItemClickListener
                       private val listener: (Champion) -> Unit
) : RecyclerView.Adapter<ChampionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ActivityChampionsItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.activity_champions_item,
                parent,
                false
        )
//        binding.onItemClickListener = onItemClickListener
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val champion = champions[position]
        holder.binding.champion = champion
        holder.binding.root.setOnClickListener({ listener(champion) })
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return champions.size
    }

    class ViewHolder(val binding: ActivityChampionsItemBinding) : RecyclerView.ViewHolder(binding.root)

//    interface OnItemClickListener {
//        fun onItemClick(champion: Champion)
//    }
}
