package com.leaguechampions.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.leaguechampions.data.local.Const
import com.leaguechampions.ui.championdetails.ChampionDetailsUiModel
import com.leaguechampions.ui.champions.ChampionUiModel
import com.squareup.picasso.Picasso

@BindingAdapter("champion")
fun loadChampionImage(ivChampion: ImageView, championDetails: ChampionDetailsUiModel?) {
    championDetails?.let {
        loadChampionImage(ivChampion, it.id, it.version)
    }
}

@BindingAdapter("champion")
fun loadChampionImage(ivChampion: ImageView, champion: ChampionUiModel?) {
    champion?.let {
        loadChampionImage(ivChampion, it.id, it.version)
    }
}

private fun loadChampionImage(ivChampion: ImageView, championId: String, championVersion: String) {
    val context = ivChampion.context
    val urlImage = UrlUtils.getImageUrl(context, championId, championVersion)
    if (Const.isGlide) {
        Glide.with(context)
                .load(urlImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivChampion)
    } else {
        Picasso.with(context)
                .load(urlImage)
                .into(ivChampion)
    }
}
