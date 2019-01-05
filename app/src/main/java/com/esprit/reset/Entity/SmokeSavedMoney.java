package com.esprit.reset.Entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.esprit.reset.LoginActivity;
import com.esprit.reset.utils.AppSingleton;

/**
 * Created by walid on 27/11/2017.
 */

public class SmokeSavedMoney {

    int savedMoney;
    int count=-1;

    public SmokeSavedMoney(final Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String smoke_money = prefs.getString("pref_smoke_money","");

         final String ServerURL = LoginActivity.ServerAddress+"reset/webservices/SmokingSavedMoney.php";
         final String TAG = "SmokeSavedMoney";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL+"?id="+LoginActivity.token+"&setting="+smoke_money, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            SmokeSavedMoney.this.savedMoney = Integer.valueOf(response.trim());
                            SmokeSavedMoney.this.count = 1;
                            Log.e(TAG,SmokeSavedMoney.this.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,"error");
                    }
                }
        );
        // Adding JsonObject request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(postRequest, REQUEST_TAG);
    }

    public SmokeSavedMoney(int savedMoney, int count) {
        this.savedMoney = savedMoney;
        this.count = count;
    }

    public int getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(int savedMoney) {
        this.savedMoney = savedMoney;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SmokeSavedMoney{" +
                "savedMoney=" + savedMoney +
                ", count=" + count +
                '}';
    }
}
