package com.genius.wasylews.airpollution;

import com.genius.wasylews.airpollution.models.ListPollutionModel;

import retrofit2.Call;


public class SO2DetailFragment extends ListDetailFragment {
    @Override
    Call<ListPollutionModel> getPollutionData() {
        return App.getApi().getSO2Pollution();
    }
}
