package com.esprit.reset.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.esprit.reset.Entity.Trophy;
import com.esprit.reset.LoginActivity;

/**
 * Created by walid on 25/01/2018.
 */

public class UserTrophy {

    public static Trophy trophy;
    public static final String ServerURL = LoginActivity.ServerAddress+"reset/webservices/";
    private static final String TAG = "Trophys";

    public UserTrophy(Context context) {
        getDrinkeRest(context);
        getSmokeRest(context);
        trophy = new Trophy();
    }

    public static void getDrinkeRest(Context context){
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL+"DrinkingRest.php?id="+LoginActivity.token, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            trophy.setDrinkeRest(Integer.valueOf(response));
                        }catch (Exception ex){
                            Log.e(TAG,ex.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(postRequest, REQUEST_TAG);
    }

    public static void getSmokeRest(Context context){
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL+"SmokingRest.php?id="+LoginActivity.token, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            trophy.setDrinkeRest(Integer.valueOf(response));
                        }catch (Exception ex){
                            Log.e(TAG,ex.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(postRequest, REQUEST_TAG);
    }


}
