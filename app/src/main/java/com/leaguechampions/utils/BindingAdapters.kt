package com.leaguechampions.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import com.squareup.picasso.Picasso

@BindingAdapter("champion")
fun loadChampionImage(ivChampion: ImageView, champion: Champion?) {
    if (champion != null) {
        if (Const.isGlide) {
            Glide.with(ivChampion.context)
                    .load(UrlUtils.getImageUrl(ivChampion.context, champion))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivChampion)
        } else {
//            picasso.load(UrlUtils.getImageUrl(this, version, champion.getId())).into(ivChampion);
            Picasso.with(ivChampion.context)
                    .load(UrlUtils.getImageUrl(ivChampion.context, champion))
                    .into(ivChampion)
        }
    }
}
