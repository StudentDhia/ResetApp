package com.walidhelaoui.resetandroidapp;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.walidhelaoui.resetandroidapp.Controller.DrinkAdapter;
import com.walidhelaoui.resetandroidapp.Controller.SmokeAdapter;
import com.walidhelaoui.resetandroidapp.Entity.DrinkingStatistics;
import com.walidhelaoui.resetandroidapp.Entity.SmokingStatistics;
import com.walidhelaoui.resetandroidapp.utils.DrinkingDataSource;
import com.walidhelaoui.resetandroidapp.utils.SmokingDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DrinkingStatisticsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private Handler mHandler;
    ProgressDialog progressDialog;
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            DrinkingStatisticsActivity.this.mHandler.postDelayed(m_Runnable, 3000);

            if (!DrinkingDataSource.drinking.isEmpty()){
                progressDialog.hide();
                DrinkAdapter adapter = new DrinkAdapter(DrinkingStatisticsActivity.this, DrinkingDataSource.drinking);
                listView.setAdapter(adapter);
                mHandler.removeCallbacksAndMessages(null);
            }
            if(DrinkingDataSource.isEmpty){
                progressDialog.hide();
                mHandler.removeCallbacksAndMessages(null);
                Toast.makeText(getApplicationContext(),"There aren't any data available right now!",Toast.LENGTH_LONG).show();
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinking_statistics);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Drinking Statistics");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,3000);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        listView = (ListView) findViewById(R.id.ListView);
        DrinkingDataSource.setDrink(this);
        DrinkAdapter adapter = new DrinkAdapter(this, DrinkingDataSource.drinking);
        listView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.statistics_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrinkAdapter adapter;
        List<DrinkingStatistics> list = DrinkingDataSource.drinking;
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.sortMax:
                Collections.sort(list, new Comparator<DrinkingStatistics>() {
                    @Override
                    public int compare(DrinkingStatistics o1, DrinkingStatistics o2) {
                        if (o1.getNumber()>o2.getNumber()){
                            return -1;
                        }else if (o1.getNumber()<o2.getNumber()){
                            return 1;
                        }else {
                            return 0;
                        }
                    }
                });
                adapter = new DrinkAdapter(this, list);
                listView.setAdapter(adapter);
                return true;
            case R.id.sortMin:
                Collections.sort(list, new Comparator<DrinkingStatistics>() {
                    @Override
                    public int compare(DrinkingStatistics o1, DrinkingStatistics o2) {
                        if (o1.getNumber()>o2.getNumber()){
                            return 1;
                        }else if (o1.getNumber()<o2.getNumber()){
                            return -1;
                        }else {
                            return 0;
                        }
                    }
                });
                adapter = new DrinkAdapter(this, list);
                listView.setAdapter(adapter);
                return true;
            case R.id.reverse:
                Collections.reverse(list);
                adapter = new DrinkAdapter(this, list);
                listView.setAdapter(adapter);
                return true;
            case R.id.hideRest:
                List<DrinkingStatistics> newList= new ArrayList<>();
                for (DrinkingStatistics statistics : list){
                    if (statistics.getNumber()>0){
                        newList.add(statistics);
                    }
                }
                adapter = new DrinkAdapter(this, newList);
                listView.setAdapter(adapter);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
