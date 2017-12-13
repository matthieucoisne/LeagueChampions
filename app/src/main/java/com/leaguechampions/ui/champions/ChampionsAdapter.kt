package com.leaguechampions.ui.champions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.leaguechampions.R
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.model.RiotResponse
import com.leaguechampions.utils.UrlUtils
import com.squareup.picasso.Picasso
import java.util.*

class ChampionsAdapter(riotResponse: RiotResponse,
                       private val picasso: Picasso,
                       private val listener: OnItemClickListener) : RecyclerView.Adapter<ChampionsAdapter.ViewHolder>() {

    private val version: String
    private val data: List<Champion>

    init {
        this.data = ArrayList(riotResponse.data.values)
        this.version = riotResponse.version
        Collections.sort(this.data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_champions_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val champion = data[position]
        holder.tvChampion.text = champion.name

        if (Const.isGlide) {
            Glide.with(context).load(UrlUtils.getImageUrl(context, version, champion.id)).into(holder.ivChampion)
        } else {
            picasso.load(UrlUtils.getImageUrl(context, version, champion.id)).into(holder.ivChampion)
        }

        holder.itemView.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                listener.onItemClick(version, data[pos])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivChampion: ImageView = view.findViewById(R.id.activity_champions_row_ivChampion)
        val tvChampion: TextView = view.findViewById(R.id.activity_champions_row_tvChampion)
    }

    interface OnItemClickListener {
        fun onItemClick(version: String, champion: Champion)
    }
}
