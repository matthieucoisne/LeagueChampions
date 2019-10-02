package com.leaguechampions.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.leaguechampions.core.data.local.Const
import com.leaguechampions.utils.UrlUtils
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
