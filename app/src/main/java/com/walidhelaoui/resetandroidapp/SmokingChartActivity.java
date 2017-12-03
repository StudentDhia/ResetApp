package com.walidhelaoui.resetandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class SmokingChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoking_chart);

        BarChart chart = (BarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(15f,0));
        barEntries.add(new BarEntry(20f,1));
        barEntries.add(new BarEntry(35f,2));
        barEntries.add(new BarEntry(10f,3));
        barEntries.add(new BarEntry(70f,4));
        barEntries.add(new BarEntry(7f,5));
        barEntries.add(new BarEntry(2f,6));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Number of Cigarettes");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("Day1");
        theDates.add("Day2");
        theDates.add("Day3");
        theDates.add("Day4");
        theDates.add("Day5");
        theDates.add("Day6");
        theDates.add("Day7");

        BarData data = new BarData(theDates,barDataSet);
        chart.setData(data);


    }
}
