package com.leaguechampions.utils;

import android.content.Context;

public final class UrlUtils {

    private UrlUtils() {}

    public static String getImageUrl(Context context, String version, String championKey) {
        return "http://ddragon.leagueoflegends.com/cdn/"+version+"/img/champion/"+championKey+".png";
    }
}
