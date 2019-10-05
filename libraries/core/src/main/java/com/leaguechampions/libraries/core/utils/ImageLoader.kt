package com.leaguechampions.libraries.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.leaguechampions.libraries.core.data.local.Const
import com.squareup.picasso.Picasso

fun loadChampionImage(ivChampion: ImageView, championId: String, championVersion: String) {
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
