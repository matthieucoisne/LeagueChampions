package com.leaguechampions.data.model;

import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;

public class Champion implements Comparable<Champion> {

    private String id;
    private String name;
    private String title;
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

    public Spanned getLore() {
        return Html.fromHtml(lore);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Champion another) {
        return name.compareTo(another.name);
    }
}
