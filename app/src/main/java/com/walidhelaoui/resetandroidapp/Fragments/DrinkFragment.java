package com.walidhelaoui.resetandroidapp.Fragments;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.walidhelaoui.resetandroidapp.DrinkingStatisticsActivity;
import com.walidhelaoui.resetandroidapp.Entity.DrinkSavedMoney;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkFragment extends Fragment {

    public DrinkSavedMoney drinkSavedMoney;

    public DrinkFragment(DrinkSavedMoney drinkSavedMoney) {
        // Required empty public constructor
        this.drinkSavedMoney = drinkSavedMoney;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drink, container, false);
        final TextView savedMoney = (TextView) view.findViewById(R.id.savedMoney);
        ValueAnimator animator = ValueAnimator.ofInt(0, drinkSavedMoney.getSavedMoney());
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                savedMoney.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
        ImageView savedMoneyImg = (ImageView) view.findViewById(R.id.savedMoneyImgDrink);
        savedMoneyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DrinkingStatisticsActivity.class));
            }
        });

        return view;
    }

}
