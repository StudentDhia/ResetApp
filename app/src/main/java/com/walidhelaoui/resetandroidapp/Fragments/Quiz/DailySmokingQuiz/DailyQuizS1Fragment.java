package com.walidhelaoui.resetandroidapp.Fragments.Quiz.DailySmokingQuiz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.appolica.flubber.Flubber;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

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
        Button stats = (Button) view.findViewById(R.id.m1_stats);

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

                // ALLEZ DANS FRAGMENT TOUT EST OK ! A FAIRE
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //((MainActivity)getActivity()).replaceFragment(new Stats1Fragment());
            }
        });
    }

}
