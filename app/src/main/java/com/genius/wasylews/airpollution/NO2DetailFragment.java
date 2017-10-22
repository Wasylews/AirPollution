package com.genius.wasylews.airpollution;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.genius.wasylews.airpollution.models.NO2PollutionModel;
import com.genius.wasylews.airpollution.models.PollutionData;

import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NO2DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_no2_detail, container, false);

        Call<NO2PollutionModel> call = App.getApi().getNO2Pollution();
        call.enqueue(new Callback<NO2PollutionModel>() {
            @Override
            public void onResponse(Call<NO2PollutionModel> call, Response<NO2PollutionModel> response) {
                TableLayout layout = view.findViewById(R.id.no2_pollution_table);

                if (response.body() == null) {
                    Toast.makeText(getActivity(), "Can't load data", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Map.Entry<String, PollutionData> item: response.body().getData().entrySet()){
                    String header = getStringByName(getActivity(), item.getKey());

                    TableRow row = createRow(header, item.getValue());
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);

                    layout.addView(row, layoutParams);
                }
            }

            private String getStringByName(Context context, String name) {
                Resources res = context.getResources();
                return res.getString(res.getIdentifier(name, "string", context.getPackageName()));
            }

            private TableRow createRow(String header, PollutionData item) {
                TableRow row = new TableRow(getActivity());

                Drawable drawable = getAttrDrawable(android.R.attr.dividerVertical);
                row.setDividerDrawable(drawable);
                row.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.f);

                TextView headerView = new TextView(getActivity());
                headerView.setText(header);
                row.addView(headerView, layoutParams);

                TextView valueView = new TextView(getActivity());
                valueView.setText(String.format(Locale.getDefault(), "%.3e", item.getValue()));
                valueView.setGravity(Gravity.CENTER);
                row.addView(valueView, layoutParams);

                TextView precisionView = new TextView(getActivity());
                precisionView.setText(String.format(Locale.getDefault(), "%.3e", item.getPrecision()));
                precisionView.setGravity(Gravity.END);
                row.addView(precisionView, layoutParams);

                return row;
            }

            private Drawable getAttrDrawable(int attr) {
                TypedArray a = getActivity().getTheme().obtainStyledAttributes(R.style.AppTheme,
                        new int[] {attr});
                int attributeResourceId = a.getResourceId(0, 0);
                Drawable drawable = getResources().getDrawable(attributeResourceId);
                a.recycle();
                return drawable;
            }

            @Override
            public void onFailure(Call<NO2PollutionModel> call, Throwable t) {
            }
        });

        return view;
    }
}
