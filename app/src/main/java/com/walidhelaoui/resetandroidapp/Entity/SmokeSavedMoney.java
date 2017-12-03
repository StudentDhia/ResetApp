package com.walidhelaoui.resetandroidapp.Entity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.resetandroidapp.Fragments.SmokeFragment;
import com.walidhelaoui.resetandroidapp.LoginActivity;
import com.walidhelaoui.resetandroidapp.utils.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by walid on 27/11/2017.
 */

public class SmokeSavedMoney {

    int savedMoney;
    int count;

    public SmokeSavedMoney(final Context context) {
         final String ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/smoking/savedMoney";
         final String TAG = "SmokeSavedMoney";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            SmokeSavedMoney.this.setSavedMoney(jsonObject.getInt("savedMoney"));
                            SmokeSavedMoney.this.setCount(jsonObject.getInt("count"));
                            Log.e(TAG,SmokeSavedMoney.this.toString());
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
