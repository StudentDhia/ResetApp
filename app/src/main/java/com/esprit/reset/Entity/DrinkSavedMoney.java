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

public class DrinkSavedMoney {

    int SavedMoney;
    int count;

    public DrinkSavedMoney(final Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String drink_money = prefs.getString("pref_alcohol_money","");

        final String ServerURL = LoginActivity.ServerAddress+"reset/webservices/DrinkingSavedMoney.php";
        final String TAG = "DrinkSavedMoney";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL+"?id="+LoginActivity.token+"&setting="+drink_money, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            int val = Integer.valueOf(response.trim());
                            Log.e("val",""+val);
                            DrinkSavedMoney.this.SavedMoney = val;
                            DrinkSavedMoney.this.count = 1;
                            Log.e(TAG,DrinkSavedMoney.this.toString());
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

    public int getSavedMoney() {
        return SavedMoney;
    }

    public void setSavedMoney(int savedMoney) {
        SavedMoney = savedMoney;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DrinkSavedMoney{" +
                "SavedMoney=" + SavedMoney +
                ", count=" + count +
                '}';
    }
}
