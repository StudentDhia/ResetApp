package com.walidhelaoui.resetandroidapp.Fragments.Quiz.DailyDrinkingQuiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.appolica.flubber.Flubber;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.thunderrise.animations.PulseAnimation;
import com.walidhelaoui.resetandroidapp.LoginActivity;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;
import com.walidhelaoui.resetandroidapp.utils.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DailyQuizA4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyQuizA4Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArcProgress arc1,arc2;
    private TextView days, infos;
    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;

    public DailyQuizA4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyQuizA4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyQuizA4Fragment newInstance(int param1, int param2) {
        DailyQuizA4Fragment fragment = new DailyQuizA4Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_quiz_a4, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        infos = (TextView) view.findViewById(R.id.msgLungs);
        arc1 = (ArcProgress) view.findViewById(R.id.arc_progress);
        arc2 = (ArcProgress) view.findViewById(R.id.arc_progress2);
        days = (TextView) view.findViewById(R.id.daysLungs);
        ImageView kuma = (ImageView) view.findViewById(R.id.dailyKuma4);
        ImageView lungs = (ImageView) view.findViewById(R.id.lung2);

        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_UP) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(kuma)                             // Apply it to the view
                .start();                                    // Start it now

        if (arc1.getProgress()<10){
            lungs.setImageResource(R.drawable.lungs1);

            PulseAnimation.create().with(lungs)
                    .setDuration(320)
                    .setRepeatCount(PulseAnimation.INFINITE)
                    .setRepeatMode(PulseAnimation.REVERSE)
                    .start();

        }else
        if(arc1.getProgress()>=10 && arc1.getProgress() <= 15){
            lungs.setImageResource(R.drawable.lungs2);

            PulseAnimation.create().with(lungs)
                    .setDuration(210)
                    .setRepeatCount(PulseAnimation.INFINITE)
                    .setRepeatMode(PulseAnimation.REVERSE)
                    .start();

        }else{
            lungs.setImageResource(R.drawable.lungs3);

            PulseAnimation.create().with(lungs)
                    .setDuration(150)
                    .setRepeatCount(PulseAnimation.INFINITE)
                    .setRepeatMode(PulseAnimation.REVERSE)
                    .start();
        }

        setData();
        wastedDays(220);

        infos.setText("Hey there !");

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(arc1.getProgress() > 15){
                    infos.setText("The nicotine level in your blood is getting too high. " +
                            "Let's stop smoking now. ou're not planning to die now, right ?");
                }

                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (arc2.getProgress() > 5){
                            infos.setText("You lungs are at "+ arc2.getProgress() + ". Let's not taint your lungs and keep them clean.");
                        }

                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {

                                infos.setText("You wasted " + days.getText());

                                Handler handler = new Handler();
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        // infos.setText("You wasted " + days.getText());
                                        startActivity(new Intent(getActivity(), MainActivity.class));
                                    }
                                };

                                handler.postDelayed(runnable, 3000);
                            }
                        };

                        handler.postDelayed(runnable, 3000);
                    }
                };

                handler.postDelayed(runnable, 3000);
            }
        };

        handler.postDelayed(runnable, 3000);


    }


    public void wastedDays(int cigs){

        days.setText(((cigs * 7)/1440) + " Days");
    }


    public void setData(){
        final String ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/drinking/quiz";
        final String TAG = "DailyQuizA4Fragment";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";

        // Request parameters to be send with post request
        StringRequest postRequest = new StringRequest(Request.Method.POST, ServerURL, // the request body, which is a JsonObject otherwise null
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
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                final JSONObject obj = new JSONObject();
                try {
                    obj.put("drinkNumber",mParam1);
                    obj.put("drinkPrice",mParam2);
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
