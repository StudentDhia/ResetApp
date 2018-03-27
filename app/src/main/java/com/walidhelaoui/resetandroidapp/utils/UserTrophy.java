package com.walidhelaoui.resetandroidapp.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.resetandroidapp.Entity.Trophy;
import com.walidhelaoui.resetandroidapp.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by walid on 25/01/2018.
 */

public class UserTrophy {

    public static Trophy trophy;
    public static final String ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/";
    private static final String TAG = "Trophys";

    public UserTrophy(Context context) {
        getDrinkeRest(context);
        getSmokeRest(context);
        trophy = new Trophy();
    }

    public static void getDrinkeRest(Context context){
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, ServerURL+"drinking/rest", // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            trophy.setDrinkeRest(jsonObject.getInt("drinkingRest"));
                            Log.e(TAG,response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + LoginActivity.token);
                return headers;
            }


        };

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(postRequest, REQUEST_TAG);
    }

    public static void getSmokeRest(Context context){
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, ServerURL+"smoking/rest", // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            trophy.setSmokeRest(jsonObject.getInt("smokingRest"));
                            Log.e(TAG,response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + LoginActivity.token);
                return headers;
            }


        };

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(postRequest, REQUEST_TAG);
    }


}
