package com.walidhelaoui.resetandroidapp.Fragments.Quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.resetandroidapp.LoginActivity;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;
import com.walidhelaoui.resetandroidapp.utils.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    private TextView TextScore;

    public ScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreFragment newInstance(int param1, String param2) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextScore = (TextView) view.findViewById(R.id.score);
        setScore(mParam1,mParam2);
        // Open SharedPrefs File in Edit Mode
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.edit().putBoolean(MainActivity.PREFS_QUIZ, true).apply();

        if(mParam2 == "cigarette") {
            if (mParam1 < 3) {
                TextScore.setText("Cigarette : Low dependance");
            } else if (mParam1 == 3 || mParam1 == 4) {
                TextScore.setText("Cigarette : Low to mod dependence");
            } else if (mParam1 == 5 || mParam1 == 6) {
                TextScore.setText("Cigarette : Moderate dependence");
            } else {
                TextScore.setText("Cigarette : High dependence");
            }
        }
        if(mParam2 == "alcohol") {
            if (mParam1 < 12) {
                TextScore.setText("Alcohol : Low dependance");
            } else if (mParam1 <16 && mParam1 > 11) {
                TextScore.setText("Alcohol : Low to mod dependence");
            } else if (mParam1 <22 && mParam1 > 15) {
                TextScore.setText("Alcohol : Moderate dependence");
            } else {
                TextScore.setText("Alcohol : High dependence");
            }
        }
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               // ((MainActivity)getActivity()).replaceFragment(new Suivi1Fragment());
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        };
        handler.postDelayed(runnable, 5000);
    }

    public void setScore(final int score,String type){
        final String ServerURL,Param;
        if (type=="cigarette"){
              ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/smoking/score";
              Param = "smokeScore";
        }else if (type == "alcohol"){
              ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/drinking/score";
              Param = "drinkScore";
        }else{
            return;
        }
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
                    obj.put(Param,score);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Log.e(TAG,obj.toString());
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
