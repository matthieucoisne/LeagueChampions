package com.leaguechampions.utils.glide

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.GlideModule
import com.leaguechampions.utils.PrefUtils

import java.io.InputStream

class GlideConfiguration : GlideModule {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
    }

    override fun registerComponents(context: Context, glide: Glide) {
        if (PrefUtils.isMockMode(context)) {
            Glide.get(context).register(GlideUrl::class.java, InputStream::class.java, GlideAssetLoader.Factory())
        }
    }
}
