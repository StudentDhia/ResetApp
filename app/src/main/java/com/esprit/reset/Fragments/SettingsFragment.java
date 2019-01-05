package com.esprit.reset.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.esprit.reset.Entity.Settings;
import com.esprit.reset.LoginActivity;
import com.esprit.reset.MainActivity;
import com.esprit.reset.R;
import com.esprit.reset.utils.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by walid on 25/12/2017.
 */

public class SettingsFragment extends PreferenceFragment {

    public static Settings settings;
    private static final String TAG = "SettingsFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = new Settings();
        addPreferencesFromResource(R.xml.settings);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String alcohol_money = prefs.getString("pref_alcohol_money","");
        String smoke_money = prefs.getString("pref_smoke_money","");
        if (alcohol_money .equals("0") || smoke_money.equals("0")) {
            getSettingsValue(getActivity());
            if (settings.getSmoking_price()!=-1&&settings.getDrinking_price()!=-1){
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                editor.putString("pref_alcohol_money", String.valueOf(settings.getDrinking_price()));
                editor.putString("pref_smoke_money", String.valueOf(settings.getSmoking_price()));
                editor.apply();
                Log.e(TAG," "+settings.getSmoking_price());
            }

        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            view.setBackgroundResource(R.drawable.backgroundlight);
            Log.e(TAG," "+settings.getSmoking_price());
        }


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(),"The Settings has been changed with success",Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String alcohol_money = prefs.getString("pref_alcohol_money","");
        String smoke_money = prefs.getString("pref_smoke_money","");
        //setData(alcohol_money,smoke_money);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String alcohol_money = prefs.getString("pref_alcohol_money","");
        String smoke_money = prefs.getString("pref_smoke_money","");
        //setData(alcohol_money,smoke_money);
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(),"The Settings has been changed with success",Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String alcohol_money = prefs.getString("pref_alcohol_money","");
        String smoke_money = prefs.getString("pref_smoke_money","");
        //setData(alcohol_money,smoke_money);
    }


    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(),"The Settings has been changed with success",Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String alcohol_money = prefs.getString("pref_alcohol_money","");
        String smoke_money = prefs.getString("pref_smoke_money","");
        //setData(alcohol_money,smoke_money);
    }



    public void setData(final String alcohol_money, final String smoke_money){
        final String ServerURL = LoginActivity.ServerAddress+"reset/web/app.php/api/setting";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";

        // Request parameters to be send with post request
        StringRequest postRequest = new StringRequest(Request.Method.POST, ServerURL, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,response);
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
            public byte[] getBody() throws AuthFailureError {
                final JSONObject obj = new JSONObject();
                try {
                    obj.put("smokingPrice",smoke_money);
                    obj.put("drinkingPrice",alcohol_money);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e(TAG,obj.toString());
                return obj.toString().trim().getBytes();

            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "Bearer " + LoginActivity.token);
                return headers;
            }

        };

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getActivity()).addToRequestQueue(postRequest, REQUEST_TAG);
    }

    public static void getSettingsValue(Context context){
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, LoginActivity.ServerURL+"setting/value", // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            settings.setSmoking_price(jsonObject.getInt("smoking_setting_price"));
                            settings.setDrinking_price(jsonObject.getInt("drinking_setting_price"));
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
