package com.walidhelaoui.resetandroidapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.resetandroidapp.Entity.SmokeSavedMoney;
import com.walidhelaoui.resetandroidapp.LoginActivity;
import com.walidhelaoui.resetandroidapp.R;
import com.walidhelaoui.resetandroidapp.utils.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    EditText smokingPrice,drinkingPrice;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

         smokingPrice = (EditText) view.findViewById(R.id.edtSmokingPrice);
         drinkingPrice = (EditText) view.findViewById(R.id.edtDrinkingPrice);

        Button saveChanges = (Button) view.findViewById(R.id.btn_setting);

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    setData();
                }
            }
        });



        return view;
    }

    public boolean validate() {
        boolean valid = true;

        String smokingPrice = (this.smokingPrice.getText().toString());
        String drinkingPrice = (this.drinkingPrice.getText().toString());

        try {
            Float.valueOf(smokingPrice);
        }catch (NumberFormatException e){
            this.smokingPrice.setError("not a valid number");
            valid = false;
        }

        try {
            Float.valueOf(drinkingPrice);
        }catch (NumberFormatException e){
            this.drinkingPrice.setError("not a valid number");
            valid = false;
        }

        return valid;
    }

    public void setData(){
        final String ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/setting";
        final String TAG = "SettingFragment";
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
                    obj.put("smokingPrice",smokingPrice.getText().toString());
                    obj.put("drinkingPrice",drinkingPrice.getText().toString());
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
        AppSingleton.getInstance(getContext()).addToRequestQueue(postRequest, REQUEST_TAG);
    }


}
