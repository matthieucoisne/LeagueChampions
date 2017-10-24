package com.leaguechampions.data.model;

import java.util.Map;

public class RiotResponse {

    private String version;
    private Map<String, Champion> data;

    public String getVersion() {
        return version;
    }

    public Map<String, Champion> getData() {
        return data;
    }
}
