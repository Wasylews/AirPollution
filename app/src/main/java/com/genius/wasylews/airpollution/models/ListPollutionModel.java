package com.genius.wasylews.airpollution.models;


import java.util.ArrayList;
import java.util.List;

public class ListPollutionModel {

    public class ListData extends PollutionData {

        private double pressure;

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }
    }

    private List<ListData> data;

    public ListPollutionModel() {
        data = new ArrayList<>();
    }

    public List<ListData> getData() {
        return data;
    }
}
