package com.leaguechampions.utils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.leaguechampions.utils.PrefUtils;

import java.io.InputStream;

public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        if (PrefUtils.isMockMode(context)) {
            Glide.get(context).register(GlideUrl.class, InputStream.class, new GlideAssetLoader.Factory());
        }
    }
}
