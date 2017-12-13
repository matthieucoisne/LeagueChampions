package com.leaguechampions.utils.glide

import android.content.Context
import android.content.res.AssetManager

import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GenericLoaderFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.stream.StreamModelLoader

import java.io.InputStream

class GlideAssetLoader(private val assetManager: AssetManager) : StreamModelLoader<GlideUrl> {

    override fun getResourceFetcher(model: GlideUrl, width: Int, height: Int): DataFetcher<InputStream> {
        return GlideAssetStreamFetcher(model, assetManager)
    }

    class Factory : ModelLoaderFactory<GlideUrl, InputStream> {
        override fun build(context: Context, factories: GenericLoaderFactory): ModelLoader<GlideUrl, InputStream> {
            return GlideAssetLoader(context.assets)
        }

        override fun teardown() {
            // Do nothing, this instance doesn't own the client.
        }
    }
}