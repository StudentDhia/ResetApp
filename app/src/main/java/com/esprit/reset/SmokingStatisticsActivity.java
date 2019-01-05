package com.esprit.reset;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.esprit.reset.Controller.SmokeAdapter;
import com.esprit.reset.Entity.SmokingStatistics;
import com.esprit.reset.utils.SmokingDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.esprit.reset.utils.SmokingDataSource.isEmpty;
import static com.esprit.reset.utils.SmokingDataSource.smoking;

public class SmokingStatisticsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private Handler mHandler;
    ProgressDialog progressDialog;
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            SmokingStatisticsActivity.this.mHandler.postDelayed(m_Runnable, 1000);

            if (!smoking.isEmpty()){
                progressDialog.hide();
                SmokeAdapter adapter = new SmokeAdapter(SmokingStatisticsActivity.this, smoking);
                listView.setAdapter(adapter);
                mHandler.removeCallbacksAndMessages(null);
            }
            if(isEmpty){
                progressDialog.hide();
                mHandler.removeCallbacksAndMessages(null);
                Toast.makeText(getApplicationContext(),"There aren't any data available right now!",Toast.LENGTH_LONG).show();
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoking_statistics);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Smoking Statistics");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,3000);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        listView = (ListView) findViewById(R.id.ListView);
        SmokingDataSource.setSmoke(this);
        SmokeAdapter adapter = new SmokeAdapter(this, smoking);
        listView.setAdapter(adapter);
        Log.e("isEmpty: ",""+isEmpty);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.statistics_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SmokeAdapter adapter;
        List<SmokingStatistics> list = SmokingDataSource.smoking;

        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.sortMax:
                Collections.sort(list, new Comparator<SmokingStatistics>() {
                    @Override
                    public int compare(SmokingStatistics o1, SmokingStatistics o2) {
                        if (o1.getNumber()>o2.getNumber()){
                            return -1;
                        }else if (o1.getNumber()<o2.getNumber()){
                            return 1;
                        }else {
                            return 0;
                        }
                    }
                });
                adapter = new SmokeAdapter(this, list);
                listView.setAdapter(adapter);
                return true;
            case R.id.sortMin:
                Collections.sort(list, new Comparator<SmokingStatistics>() {
                    @Override
                    public int compare(SmokingStatistics o1, SmokingStatistics o2) {
                        if (o1.getNumber()>o2.getNumber()){
                            return 1;
                        }else if (o1.getNumber()<o2.getNumber()){
                            return -1;
                        }else {
                            return 0;
                        }
                    }
                });
                adapter = new SmokeAdapter(this, list);
                listView.setAdapter(adapter);
                return true;
            case R.id.reverse:
                Collections.reverse(list);
                adapter = new SmokeAdapter(this, list);
                listView.setAdapter(adapter);
                return true;
            case R.id.hideRest:
                List<SmokingStatistics> newList= new ArrayList<>();
                for (SmokingStatistics statistics : list){
                    if (statistics.getNumber()>0){
                        newList.add(statistics);
                    }
                }
                adapter = new SmokeAdapter(this, newList);
                listView.setAdapter(adapter);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
