package com.leaguechampions.utils.picasso

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.SystemClock
import android.util.LruCache
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Request
import com.squareup.picasso.RequestHandler
import retrofit2.mock.NetworkBehavior
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * A Picasso [com.squareup.picasso.Downloader] which loads images from assets but attempts to emulate the
 * subtleties of a real HTTP client and its disk cache.
 *
 * Images *must* be in the form `mock:///path/to/asset.png`.
 */
class PicassoMockRequestHandler(private val behavior: NetworkBehavior,
                                private val assetManager: AssetManager) : RequestHandler() {

    companion object {
        private val DISK_CACHE_SIZE = 50 * 1024 * 1024
    }

    /** Emulate the disk cache by storing the URLs in an LRU using its size as the value.  */
    private val emulatedDiskCache = object : LruCache<String, Long>(DISK_CACHE_SIZE) {
        override fun sizeOf(key: String, value: Long?): Int {
            return Math.min(value!!, Integer.MAX_VALUE.toLong()).toInt()
        }
    }

    override fun canHandleRequest(data: Request): Boolean {
        return data.uri.scheme == "mock"
    }

    override fun load(request: Request, networkPolicy: Int): RequestHandler.Result? {
        // Grab only the path sans leading slash.
        val imagePath = request.uri.path.substring(1)

        // Check the disk cache for the image. A non-null return value indicates a hit.
        val cacheHit = emulatedDiskCache.get(imagePath) != null

        // If there's a hit, grab the image stream and return it.
        if (cacheHit) {
            return RequestHandler.Result(loadBitmap(imagePath), Picasso.LoadedFrom.DISK)
        }

        // If we are not allowed to hit the network and the cache missed return a big fat nothing.
        if (NetworkPolicy.isOfflineOnly(networkPolicy)) {
            return null
        }

        // If we got this far there was a cache miss and hitting the network is required. See if we need
        // to fake an network error.
        if (behavior.calculateIsFailure()) {
            SystemClock.sleep(behavior.calculateDelay(TimeUnit.MILLISECONDS))
            throw IOException("Fake network error!")
        }

        // We aren't throwing a network error so fake a round trip delay.
        SystemClock.sleep(behavior.calculateDelay(TimeUnit.MILLISECONDS))

        // Since we cache missed put it in the LRU.
        val fileDescriptor = assetManager.openFd(imagePath)
        val size = fileDescriptor.length
        fileDescriptor.close()

        emulatedDiskCache.put(imagePath, size)

        // Grab the image stream and return it.
        return RequestHandler.Result(loadBitmap(imagePath), Picasso.LoadedFrom.NETWORK)
    }

    private fun loadBitmap(imagePath: String): Bitmap {
        return BitmapFactory.decodeStream(assetManager.open(imagePath))
    }
}