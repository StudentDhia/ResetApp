package com.walidhelaoui.resetandroidapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.walidhelaoui.resetandroidapp.Entity.DrinkingStatistics;
import com.walidhelaoui.resetandroidapp.utils.DrinkingDataSource;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class DrinkingChartActivity extends AppCompatActivity {

    private static final String TAG = "DrinkingChartActivity";
    private Handler mHandler;
    BarChart chart;
    GifImageView loading;
    private Toolbar toolbar;
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            DrinkingChartActivity.this.mHandler.postDelayed(m_Runnable, 3000);

            if (!DrinkingDataSource.drinking.toString().isEmpty()){
                chart = (BarChart) findViewById(R.id.chart);
                chart.setVisibility(View.VISIBLE);
                loading = (GifImageView) findViewById(R.id.loading);
                loading.setVisibility(View.GONE);
                Log.e(TAG, DrinkingDataSource.drinking.toString());
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                ArrayList<String> theDates = new ArrayList<>();
                int i=0;
                for(DrinkingStatistics statistics : DrinkingDataSource.drinking){
                    barEntries.add(new BarEntry(Float.valueOf(statistics.getNumber()),i));
                    i++;
                    theDates.add("Day"+i);
                }
                BarDataSet barDataSet = new BarDataSet(barEntries,"Number of Cigarettes");
                BarData data = new BarData(theDates,barDataSet);
                chart.setData(data);
                mHandler.removeCallbacksAndMessages(null);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinking_chart);

        chart = (BarChart) findViewById(R.id.chart);
        chart.setVisibility(View.INVISIBLE);
        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,3000);
        DrinkingDataSource.setDrink(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Drinking Chart");
    }
}
