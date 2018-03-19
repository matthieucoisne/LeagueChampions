package com.leaguechampions.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leaguechampions.data.local.Const;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.utils.UrlUtils;
import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter({"champion"})
    public static void loadChampionImage(ImageView ivChampion, Champion champion) {
        if (champion != null) {
            if (Const.isGlide) {
                Glide.with(ivChampion.getContext()).load(UrlUtils.getImageUrl(ivChampion.getContext(), champion)).into(ivChampion);
            } else {
//                picasso.load(UrlUtils.getImageUrl(ivChampion.getContext(), champion)).into(ivChampion);
                Picasso.with(ivChampion.getContext()).load(UrlUtils.getImageUrl(ivChampion.getContext(), champion)).into(ivChampion);
            }
        }
    }
}
