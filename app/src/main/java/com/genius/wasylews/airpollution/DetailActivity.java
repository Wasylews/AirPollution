package com.genius.wasylews.airpollution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    static final String EXTRA_DATA_TYPE = "DetailActivityDataType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int fragmentType = intent.getIntExtra(EXTRA_DATA_TYPE, 0);
        Fragment fragment = createFragment(fragmentType);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    private Fragment createFragment(int type) {
        switch (type) {
            case R.id.co_button:
                return new CODetailFragment();
            case R.id.o3_button:
                return new O3DetailFragment();
            case R.id.so2_button:
                return new SO2DetailFragment();
            case R.id.no2_button:
                return new NO2DetailFragment();
            default:
                return null;
        }
    }
}
