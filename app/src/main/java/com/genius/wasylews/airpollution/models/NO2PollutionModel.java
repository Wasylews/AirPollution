package com.genius.wasylews.airpollution.models;

import java.util.HashMap;
import java.util.Map;

public class NO2PollutionModel {

    private Map<String, PollutionData> data;

    public NO2PollutionModel() {
        data = new HashMap<>();
    }

    public Map<String, PollutionData> getData() {
        return data;
    }
}
