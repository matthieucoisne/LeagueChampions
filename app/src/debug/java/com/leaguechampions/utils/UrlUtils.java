package com.leaguechampions.utils;

import android.content.Context;

import com.leaguechampions.core.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class UrlUtils {

    private UrlUtils() {}

    private static final Random rand = new Random();
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

    public static String getImageUrl(Context context, String version, String championKey) {
        if (PrefUtils.isMockMode(context)) {
            if (!champions.contains(championKey)) {
                championKey = champions.get(rand.nextInt(champions.size()));
            }
            String filename = "ic_poro_" + championKey.toLowerCase() + ".png";
            return (Const.isGlide ? "http" : "mock") + ":///images/" + filename;
        } else {
            return "http://ddragon.leagueoflegends.com/cdn/"+version+"/img/champion/"+championKey+".png";
        }
    }
}
