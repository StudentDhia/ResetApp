package com.walidhelaoui.resetandroidapp.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.resetandroidapp.Entity.SmokeSavedMoney;
import com.walidhelaoui.resetandroidapp.Entity.SmokingStatistics;
import com.walidhelaoui.resetandroidapp.LoginActivity;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.SmokingStatisticsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walid on 27/11/2017.
 */

public class SmokingDataSource {

    private static HashMap<String, List<SmokingStatistics>> smoke = new HashMap<>();
    public static List<SmokingStatistics> smoking = new ArrayList<>();

    public static void setSmoke(final Context context) {
        smoking = new ArrayList<>();

        final String ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/smoking/all";
        final String TAG = "SmokingDataSource";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                           // SmokeSavedMoney.this.setSavedMoney(jsonObject.getInt("savedMoney"));
                           // SmokeSavedMoney.this.setCount(jsonObject.getInt("count"));
                            for (int i = 0; i < jsonArray.length() ; i++) {
                                Log.e(TAG,String.valueOf(i));
                                SmokingStatistics smokingStatistics = new SmokingStatistics();
                                smokingStatistics.setNumber(jsonArray.getJSONObject(i).getInt("number"));
                                smokingStatistics.setDate(jsonArray.getJSONObject(i).getString("date"));
                                smokingStatistics.setPrice(jsonArray.getJSONObject(i).getLong("price"));
                                smoking.add(smokingStatistics);
                            }
                            Log.e(TAG,jsonArray.toString());
                           // Log.e(TAG,jsonArray.getJSONArray(1).getJSONObject(1).toString());
                            Toast.makeText(context,"alala",Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
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
