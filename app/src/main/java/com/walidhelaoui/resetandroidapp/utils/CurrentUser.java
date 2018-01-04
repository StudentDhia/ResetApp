package com.walidhelaoui.resetandroidapp.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.resetandroidapp.Entity.Compte;
import com.walidhelaoui.resetandroidapp.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by walid on 16/11/2017.
 */

public class CurrentUser {

    public static final String ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/user";
    private static final String TAG = "CurrentUser";
    public static Compte user = new Compte();
    public CurrentUser(final Context context) {
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, ServerURL, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            CurrentUser.user.setUsername(jsonObject.getString("username"));
                            CurrentUser.user.setEmail(jsonObject.getString("email"));
                            CurrentUser.user.setEnable(jsonObject.getBoolean("enabled"));
                            CurrentUser.user.setSmoker(jsonObject.getBoolean("smoker"));
                            CurrentUser.user.setDrinker(jsonObject.getBoolean("drinker"));
                            CurrentUser.user.setDrink_score(jsonObject.getLong("drink_score"));
                            CurrentUser.user.setSmoke_score(jsonObject.getLong("smoke_score"));
                           Log.e(TAG,user.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"User information does not load please check your connection",Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,LoginActivity.class));
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
