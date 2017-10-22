package com.genius.wasylews.airpollution;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.genius.wasylews.airpollution.models.O3PollutionModel;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class O3DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_o3_detail, container, false);

        Call<O3PollutionModel> data = App.getApi().getO3Pollution();
        data.enqueue(new Callback<O3PollutionModel>() {
            @Override
            public void onResponse(Call<O3PollutionModel> call, Response<O3PollutionModel> response) {
                TextView valueView = view.findViewById(R.id.o3_value_text_view);

                if (response.body() == null) {
                    Toast.makeText(getActivity(), "Can't load data", Toast.LENGTH_SHORT).show();
                    return;
                }

                valueView.setText(String.format(Locale.getDefault(), "%.3e", response.body().getData()));
            }

            @Override
            public void onFailure(Call<O3PollutionModel> call, Throwable t) {
            }
        });

        return view;
    }
}
