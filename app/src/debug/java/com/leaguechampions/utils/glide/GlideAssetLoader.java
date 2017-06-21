package com.leaguechampions.utils.glide;

import android.content.Context;
import android.content.res.AssetManager;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.InputStream;

public class GlideAssetLoader implements StreamModelLoader<GlideUrl> {

    private final AssetManager assetManager;

    public GlideAssetLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        return new GlideAssetStreamFetcher(model, assetManager);
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new GlideAssetLoader(context.getAssets());
        }

        @Override
        public void teardown() {
            // Do nothing, this instance doesn't own the client.
        }
    }
}