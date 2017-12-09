package com.walidhelaoui.resetandroidapp.Fragments;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.walidhelaoui.resetandroidapp.Entity.SmokeSavedMoney;
import com.walidhelaoui.resetandroidapp.Fragments.Quiz.DailySmokingQuiz.DailyQuizS1Fragment;
import com.walidhelaoui.resetandroidapp.LoginActivity;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;
import com.walidhelaoui.resetandroidapp.SmokingChartActivity;
import com.walidhelaoui.resetandroidapp.SmokingStatisticsActivity;
import com.walidhelaoui.resetandroidapp.utils.AppSingleton;
import com.walidhelaoui.resetandroidapp.utils.CurrentUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmokeFragment extends Fragment {

    public SmokeSavedMoney smokeSavedMoney;

    public SmokeFragment(Context context) {
        // Required empty public constructor
         smokeSavedMoney = new SmokeSavedMoney(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_smoke, container, false);

        final TextView savedMoney = (TextView) view.findViewById(R.id.savedMoney);
        ValueAnimator animator = ValueAnimator.ofInt(0, smokeSavedMoney.getSavedMoney());
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                savedMoney.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();

        ImageView savedMoneyImg = (ImageView) view.findViewById(R.id.savedMoneyImg);
        savedMoneyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), SmokingStatisticsActivity.class));
            }
        });

        ImageView charts = (ImageView) view.findViewById(R.id.charts);
        charts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SmokingChartActivity.class));
            }
        });

        ImageView quizImg = (ImageView) view.findViewById(R.id.quizImg);
        quizImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(new DailyQuizS1Fragment());
            }
        });
        return view;



    }

}
