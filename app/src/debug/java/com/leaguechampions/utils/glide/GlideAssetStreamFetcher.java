package com.leaguechampions.utils.glide;

import android.content.res.AssetManager;
import android.net.Uri;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;

import java.io.IOException;
import java.io.InputStream;

public class GlideAssetStreamFetcher implements DataFetcher<InputStream> {
    private final GlideUrl url;
    private final AssetManager assetManager;
    private InputStream stream;

    public GlideAssetStreamFetcher(GlideUrl url, AssetManager assetManager) {
        this.url = url;
        this.assetManager = assetManager;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {
        stream = assetManager.open(Uri.parse(url.toStringUrl()).getPath().substring(1));
        return stream;
    }

    @Override
    public void cleanup() {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            // Ignored
        }
    }

    @Override
    public String getId() {
        return url.getCacheKey();
    }

    @Override
    public void cancel() {
    }
}
