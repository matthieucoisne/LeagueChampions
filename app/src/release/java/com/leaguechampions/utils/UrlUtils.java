package com.leaguechampions.utils;

import android.content.Context;

import com.leaguechampions.data.local.Const;
import com.leaguechampions.data.model.Champion;

public final class UrlUtils {

    private UrlUtils() {}

    public static String getImageUrl(Context context, Champion champion) {
        return Const.URL_BASE + "cdn/" + champion.getVersion() + "/img/champion/" + champion.getId() + ".png";
    }
}
