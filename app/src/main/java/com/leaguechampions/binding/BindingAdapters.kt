package com.leaguechampions.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import com.leaguechampions.utils.UrlUtils
import com.squareup.picasso.Picasso

@BindingAdapter("champion")
fun loadChampionImage(ivChampion: ImageView, champion: Champion?) {
    if (champion != null) {
        if (Const.isGlide) {
            Glide.with(ivChampion.context).load(UrlUtils.getImageUrl(ivChampion.context, "8.5.2", champion.id)).into(ivChampion)
        } else {
//            picasso.load(UrlUtils.getImageUrl(this, version, champion.getId())).into(ivChampion);
            Picasso.with(ivChampion.context).load(UrlUtils.getImageUrl(ivChampion.context, "8.5.2", champion.id)).into(ivChampion)
        }
    }
}
