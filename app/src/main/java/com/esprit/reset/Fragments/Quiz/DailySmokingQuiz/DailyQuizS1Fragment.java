package com.esprit.reset.Fragments.Quiz.DailySmokingQuiz;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.appolica.flubber.Flubber;
import com.esprit.reset.LoginActivity;
import com.esprit.reset.MainActivity;
import com.esprit.reset.R;
import com.esprit.reset.utils.AppSingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyQuizS1Fragment extends Fragment {


    public DailyQuizS1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_quiz_s1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button r1 = (Button) view.findViewById(R.id.m1_reponse1);
        Button r2 = (Button) view.findViewById(R.id.m1_reponse2);

        ImageView kuma = (ImageView) view.findViewById(R.id.dailyKuma1);

        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_UP) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(kuma)                             // Apply it to the view
                .start();                                    // Start it now


        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AJOUTER DANS LA BD +1 CIGARETTE A FAIRE !

                ((MainActivity)getActivity()).replaceFragment(new DailyQuizS2Fragment());
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });


    }

    public void setData(){
        final String ServerURL = LoginActivity.ServerAddress+"reset/webservices/DailyQuizS1.php";
        final String TAG = "DailyQuizS4Fragment";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";

        // Request parameters to be send with post request
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL+"?id="+LoginActivity.token, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,response);
                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
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
        AppSingleton.getInstance(getContext()).addToRequestQueue(postRequest, REQUEST_TAG);
    }
}
