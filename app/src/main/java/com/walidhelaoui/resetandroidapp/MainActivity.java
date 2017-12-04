package com.walidhelaoui.resetandroidapp;

import android.content.Intent;
import android.os.Handler;
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
import com.walidhelaoui.resetandroidapp.Fragments.DrinkFragment;
import com.walidhelaoui.resetandroidapp.Fragments.ProfileFragment;
import com.walidhelaoui.resetandroidapp.Fragments.SettingFragment;
import com.walidhelaoui.resetandroidapp.Fragments.SmokeFragment;
import com.walidhelaoui.resetandroidapp.utils.CurrentUser;

import com.roughike.bottombar.BottomBar;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    GifImageView loading;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private static final String TAG = "LoginActivity";
    private Handler mHandler;
    TextView tv_email;
    public DrinkSavedMoney drinkSavedMoney;
    BottomBar bottomBar;
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
                replaceFragment(new SmokeFragment(MainActivity.this));
                mHandler.removeCallbacksAndMessages(null);
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,3000);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.contentDrawer);
        relativeLayout.setVisibility(View.INVISIBLE);
        drinkSavedMoney = new DrinkSavedMoney(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        //bottomBar.setVisibility(View.INVISIBLE);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_smoke:
                        replaceFragment(new SmokeFragment(MainActivity.this));
                        break;
                    case R.id.tab_drink:
                        replaceFragment(new DrinkFragment(drinkSavedMoney));
                        break;
                    case R.id.tab_profile:
                        replaceFragment(new ProfileFragment());
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
                        replaceFragment(new SettingFragment());
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
        ft.replace(R.id.contentDrawer, fragment);
        ft.commit();
    }
}
