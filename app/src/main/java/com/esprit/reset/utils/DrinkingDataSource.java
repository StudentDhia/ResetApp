package com.esprit.reset.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.esprit.reset.Entity.DrinkingStatistics;
import com.esprit.reset.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walid on 28/11/2017.
 */

public class DrinkingDataSource {

    public static List<DrinkingStatistics> drinking = new ArrayList<>();
    public static boolean isEmpty=false;
    public static void setDrink(final Context context) {
        drinking = new ArrayList<>();

        final String ServerURL = LoginActivity.ServerAddress+"reset/webservices/DrinkingDataSource.php";
        final String TAG = "DrinkingDataSource";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL+"?id="+LoginActivity.token, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            JSONArray jsonArray = data.getJSONArray("drinking_statistics");
                            if(!response.equals("[]")){
                                isEmpty=false;
                            }else {
                                isEmpty=true;
                            }
                            Log.e(TAG,response);
                            for (int i = 0; i < jsonArray.length() ; i++) {
                                Log.e(TAG,String.valueOf(i));
                                DrinkingStatistics drinkingStatistics = new DrinkingStatistics();
                                drinkingStatistics.setNumber(jsonArray.getJSONObject(i).getInt("number"));
                                drinkingStatistics.setDate(jsonArray.getJSONObject(i).getString("date"));
                                drinkingStatistics.setPrice(jsonArray.getJSONObject(i).getLong("price"));
                                drinking.add(drinkingStatistics);
                            }
                            Log.e(TAG,jsonArray.toString());
                        } catch (JSONException e) {
                            isEmpty=true;
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
