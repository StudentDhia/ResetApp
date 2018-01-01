package com.walidhelaoui.resetandroidapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.OnTabSelectListener;
import com.walidhelaoui.resetandroidapp.Entity.DrinkSavedMoney;
import com.walidhelaoui.resetandroidapp.Entity.SmokeSavedMoney;
import com.walidhelaoui.resetandroidapp.Fragments.DrinkFragment;
import com.walidhelaoui.resetandroidapp.Fragments.GameFragment;
import com.walidhelaoui.resetandroidapp.Fragments.ProfileFragment;
import com.walidhelaoui.resetandroidapp.Fragments.Quiz.QuizFragment;
import com.walidhelaoui.resetandroidapp.Fragments.SettingFragment;
import com.walidhelaoui.resetandroidapp.Fragments.SettingsFragment;
import com.walidhelaoui.resetandroidapp.Fragments.SmokeFragment;
import com.walidhelaoui.resetandroidapp.Fragments.Trophys.TrophysFragment;
import com.walidhelaoui.resetandroidapp.utils.CurrentUser;

import com.roughike.bottombar.BottomBar;

import java.util.Calendar;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    GifImageView loading;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private static final String TAG = "LoginActivity";
    private Handler mHandler;
    TextView tv_email;
    public DrinkSavedMoney drinkSavedMoney;
    public SmokeSavedMoney smokeSavedMoney;
    public static BottomBar bottomBar;
    public static final String PREFS_QUIZ = "prefs_quiz";
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            MainActivity.this.mHandler.postDelayed(m_Runnable, 3000);

            if (!CurrentUser.user.toString().equals("")){
                Log.e(TAG, CurrentUser.user.getUsername());
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.contentDrawer);
                relativeLayout.setVisibility(View.VISIBLE);
                loading = (GifImageView) findViewById(R.id.loading);
                loading.setVisibility(View.GONE);
                tv_email.setText(CurrentUser.user.getEmail());
                //Mode Private allows you to save only one data in the file
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                Boolean quizPrefs = prefs.getBoolean(PREFS_QUIZ,false);
                if(quizPrefs){
                    toolbar.setVisibility(View.VISIBLE);
                    replaceFragment(new SmokeFragment(smokeSavedMoney));
                }else {
                    bottomBar.setVisibility(View.INVISIBLE);
                    toolbar.setVisibility(View.INVISIBLE);
                    replaceFragment(new QuizFragment());
                }
                mHandler.removeCallbacksAndMessages(null);
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set default values of settings
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // get the notification time
        String notification_time = prefs.getString("pref_notification_time","");
        // get the state of the notifications
        Boolean notification = prefs.getBoolean("pref_notification",true);
        Log.e(TAG+" notif",notification.toString());
        if (notification){
            motivationProgram1(notification_time);
        }

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,3000);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.contentDrawer);
        relativeLayout.setVisibility(View.INVISIBLE);
        drinkSavedMoney = new DrinkSavedMoney(this);
        smokeSavedMoney = new SmokeSavedMoney(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        //bottomBar.setVisibility(View.INVISIBLE);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_smoke:
                        replaceFragment(new SmokeFragment(smokeSavedMoney));
                        break;
                    case R.id.tab_drink:
                        replaceFragment(new DrinkFragment(drinkSavedMoney));
                        break;
                    case R.id.tab_profile:
                        replaceFragment(new ProfileFragment());
                        break;
                    case R.id.tab_game:
                        replaceFragment(new GameFragment());
                        break;
                    case R.id.tab_trophy:
                        replaceFragment(new TrophysFragment());
                        break;
                }
            }
        });

        initNavigationDrawer();
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                        //replaceFragment(new SmokeFragment(MainActivity.this));
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        drawerLayout.closeDrawers();
                        bottomBar.setVisibility(View.VISIBLE);
                        break;
                    case R.id.settings:
                       // replaceFragment(new SettingFragment());
                        getFragmentManager().beginTransaction()
                                .replace(R.id.contentDrawer, new SettingsFragment())
                                .commit();

                        drawerLayout.closeDrawers();
                        bottomBar.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.logout:
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText(CurrentUser.user.getEmail());
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contentDrawer, fragment).addToBackStack(null);
        ft.commit();
    }

    ////////////////////////////////////////////////////////////////////////////////

    public void createNotification2(String msg) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent notificationIntent  = new Intent(this, MainActivity.class);

        //Uri alarmSound = getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //Object mNotificationManager = getApplication().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationIntent .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pIntent = PendingIntent.getActivity(this,0 , notificationIntent , PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("NOTIFICATION")
                .setContentText(msg).setSmallIcon(R.drawable.notif_icone)
                .setContentIntent(pIntent)
                .addAction(R.drawable.notif_icone, "quiz time", pIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }

    public void createNotification(String msg) {

        //Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), getIntent(), 0);

        // Build notification
        Notification noti = new Notification.Builder(this)
                .setContentTitle("NOTIFICATION")
                .setContentText(msg).setSmallIcon(R.drawable.notif_icone).build();
        //.setContentIntent(pIntent)
        //.addAction(R.drawable.notif_icone, "And more", pIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }

    public void motivationProgram1(String notification_time){

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        switch (hour)
        {
            case 9:
                createNotification("Let's go for another no smoking day !");
                break;
            case 12:
                createNotification("no smoking breaks !");
                break;
            default:
        }

        try {
            int time = Integer.getInteger(notification_time);
            if (hour==time){
                createNotification2("Did you smoke today ?");
            }
        }catch (Exception e){
            if (hour==8){
                createNotification2("Did you smoke today ?");
            }
        }
    }
}
