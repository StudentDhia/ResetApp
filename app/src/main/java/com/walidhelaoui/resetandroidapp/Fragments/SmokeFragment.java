package com.walidhelaoui.resetandroidapp.Fragments;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
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
import com.walidhelaoui.resetandroidapp.Fragments.Trophys.TrophysFragment;
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

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import su.levenetc.android.badgeview.BadgeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmokeFragment extends Fragment {

    public SmokeSavedMoney smokeSavedMoney;

    public SmokeFragment(SmokeSavedMoney smokeSavedMoney) {
        // Required empty public constructor
         this.smokeSavedMoney = smokeSavedMoney;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_smoke, container, false);




        return view;



    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inflate the layout for this fragment

        final TextView welcome = (TextView) view.findViewById(R.id.Welcome);
        final ImageView redbar1 = (ImageView) view.findViewById(R.id.redbar1);
        final ImageView redbar2 = (ImageView) view.findViewById(R.id.redbar2);

        final TextView welcome2 = (TextView) view.findViewById(R.id.Welcome2);
        final ImageView redbar1A = (ImageView) view.findViewById(R.id.redbarA1);
        final ImageView redbar2A = (ImageView) view.findViewById(R.id.redbarA2);

        final TextView money = (TextView) view.findViewById(R.id.moneySaved);
        ValueAnimator animatorSavedMoney = ValueAnimator.ofInt(0, smokeSavedMoney.getSavedMoney());
        animatorSavedMoney.setDuration(2000);
        animatorSavedMoney.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                money.setText(animation.getAnimatedValue().toString()+"$");
            }
        });
        animatorSavedMoney.start();


        final BadgeView badgeView = (BadgeView) view.findViewById(R.id.badgeMessage);

        //money.setText(GETMONEYFROMDB);


        PulsatorLayout pulsator = (PulsatorLayout) view.findViewById(R.id.pulsator);
        pulsator.start();

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(5000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = welcome.getWidth();
                final float translationX = width * progress;
                welcome.setTranslationX(translationX);
                welcome2.setTranslationX(translationX - width);
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = redbar1.getWidth();
                final float translationX = width * progress;
                redbar1.setTranslationX(-translationX);
                redbar1A.setTranslationX(-translationX + width);
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = redbar2.getWidth();
                final float translationX = width * progress;
                redbar2.setTranslationX(-translationX);
                redbar2A.setTranslationX(-translationX + width);
            }
        });
        animator.start();

        ImageView savedMoneyImg = (ImageView) view.findViewById(R.id.smoking_savedMoneyImg);
        savedMoneyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SmokingStatisticsActivity.class));
            }
        });

        ImageView charts = (ImageView) view.findViewById(R.id.smoking_charts);
        charts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SmokingChartActivity.class));

            }
        });

        ImageView quizImg = (ImageView) view.findViewById(R.id.smoking_quizImg);
        quizImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.bottomBar.setVisibility(View.GONE);
                ((MainActivity)getActivity()).replaceFragment(new DailyQuizS1Fragment());

            }
        });

        new BadgeView.AnimationSet(badgeView).add("1", 2500).play();

    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.bottomBar.setVisibility(View.VISIBLE);
    }
}
