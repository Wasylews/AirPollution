package com.genius.wasylews.airpollution;


import com.genius.wasylews.airpollution.models.*;

import retrofit2.Call;
import retrofit2.http.GET;

interface PollutionService {
    @GET("co/0.0,10.0/current.json")
    Call<ListPollutionModel> getCOPollution();

    @GET("o3/0.0,10.0/current.json")
    Call<O3PollutionModel> getO3Pollution();

    @GET("so2/0.0,10.0/current.json")
    Call<ListPollutionModel> getSO2Pollution();

    @GET("no2/0.0,10.0/current.json")
    Call<NO2PollutionModel> getNO2Pollution();
}