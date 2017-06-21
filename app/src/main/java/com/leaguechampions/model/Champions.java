package com.leaguechampions.model;

import java.util.Map;

public class Champions {

    private String version;
    private Map<String, Champion> data;

    public String getVersion() {
        return version;
    }

    public Map<String, Champion> getData() {
        return data;
    }
}
