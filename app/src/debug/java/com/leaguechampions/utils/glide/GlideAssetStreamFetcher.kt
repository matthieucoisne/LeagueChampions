package com.leaguechampions.utils.glide

import android.content.res.AssetManager
import android.net.Uri

import com.bumptech.glide.Priority
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl

import java.io.IOException
import java.io.InputStream

class GlideAssetStreamFetcher(private val url: GlideUrl,
                              private val assetManager: AssetManager) : DataFetcher<InputStream> {

    private var stream: InputStream? = null

    override fun loadData(priority: Priority): InputStream? {
        stream = assetManager.open(Uri.parse(url.toStringUrl()).path.substring(1))
        return stream
    }

    override fun cleanup() {
        try {
            if (stream != null) {
                stream!!.close()
            }
        } catch (e: IOException) {
            // Ignored
        }
    }

    override fun getId(): String {
        return url.cacheKey
    }

    override fun cancel() {
    }
}
