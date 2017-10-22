package com.genius.wasylews.airpollution;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.genius.wasylews.airpollution.models.ListPollutionModel;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class ListDetailFragment extends Fragment {

    class ListViewHolder extends RecyclerView.ViewHolder {

        private final TextView valueTextView;
        private final TextView precisionTextView;
        private final TextView pressureTextView;

        public ListViewHolder(View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.value_text_view);
            precisionTextView = itemView.findViewById(R.id.precision_text_view);
            pressureTextView = itemView.findViewById(R.id.pressure_text_view);
        }

        public void bindData(ListPollutionModel.ListData data) {
            valueTextView.setText(String.format(Locale.getDefault(), "%.3e", data.getValue()));
            precisionTextView.setText(String.format(Locale.getDefault(), "%.3e", data.getPrecision()));
            pressureTextView.setText(String.format(Locale.getDefault(), "%.3e", data.getPressure()));
        }
    }

    class ListDataAdapter extends RecyclerView.Adapter<ListViewHolder> {

        private final ListPollutionModel data;

        public ListDataAdapter(ListPollutionModel data) {
            this.data = data;
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.detail_list_row, parent, false);

            return new ListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            ListPollutionModel.ListData item = data.getData().get(position);
            holder.bindData(item);
        }

        @Override
        public int getItemCount() {
            return data.getData().size();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_detail, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<ListPollutionModel> data = getPollutionData();
        data.enqueue(new Callback<ListPollutionModel>() {
            @Override
            public void onResponse(Call<ListPollutionModel> call, Response<ListPollutionModel> response) {
                ListDataAdapter adapter = new ListDataAdapter(response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListPollutionModel> call, Throwable t) {

            }
        });

        return view;
    }

    abstract Call<ListPollutionModel> getPollutionData();
}
