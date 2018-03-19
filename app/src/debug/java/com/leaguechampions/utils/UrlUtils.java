package com.leaguechampions.utils;

import android.content.Context;

import com.leaguechampions.data.local.Const;
import com.leaguechampions.data.model.Champion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class UrlUtils {

    private UrlUtils() {}

    private static final List<String> champions = new ArrayList<String>(17) {{
            add("Amumu");
            add("Corki");
            add("Garen");
            add("Graves");
            add("JarvanIV");
            add("LeeSin");
            add("Lulu");
            add("MissFortune");
            add("Nasus");
            add("Riven");
            add("Shaco");
            add("Sona");
            add("Teemo");
            add("Tryndamere");
            add("Vladimir");
            add("Zed");
            add("Ziggs");
    }};

    public static String getImageUrl(Context context, Champion champion) {
        String championId = champion.getId();
        if (PrefUtils.isMockMode(context)) {
            if (!champions.contains(championId)) {
                championId = champions.get(new Random().nextInt(champions.size()));
            }
            String filename = "ic_poro_" + championId.toLowerCase() + ".png";
            return (Const.isGlide ? "http" : "mock") + ":///images/" + filename;
        } else {
            return Const.URL_BASE+"cdn/"+champion.getVersion()+"/img/champion/"+championId+".png";
        }
    }
}
