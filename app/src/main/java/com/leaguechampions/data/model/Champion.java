package com.leaguechampions.data.model;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;

public class Champion implements Comparable<Champion> {

    private String id;
    private String name;
    private String title;
    private String version;
    private String lore;

    public Champion(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @SuppressWarnings("deprecation")
    public Spanned getLore() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(lore, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(lore);
        }
    }

    @Override
    public int compareTo(@NonNull Champion another) {
        return name.compareTo(another.name);
    }
}
