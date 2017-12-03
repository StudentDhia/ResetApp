package com.walidhelaoui.resetandroidapp;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.walidhelaoui.resetandroidapp.Controller.DrinkAdapter;
import com.walidhelaoui.resetandroidapp.Controller.SmokeAdapter;
import com.walidhelaoui.resetandroidapp.utils.DrinkingDataSource;
import com.walidhelaoui.resetandroidapp.utils.SmokingDataSource;

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
}
