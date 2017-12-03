package com.walidhelaoui.resetandroidapp.Entity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.resetandroidapp.LoginActivity;
import com.walidhelaoui.resetandroidapp.utils.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by walid on 27/11/2017.
 */

public class DrinkSavedMoney {

    int SavedMoney;
    int count;

    public DrinkSavedMoney(final Context context) {
        final String ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/drinking/savedMoney";
        final String TAG = "DrinkSavedMoney";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            DrinkSavedMoney.this.setSavedMoney(jsonObject.getInt("savedMoney"));
                            DrinkSavedMoney.this.setCount(jsonObject.getInt("count"));
                            Log.e(TAG,DrinkSavedMoney.this.toString());

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
