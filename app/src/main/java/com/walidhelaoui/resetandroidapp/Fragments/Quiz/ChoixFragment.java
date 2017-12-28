package com.walidhelaoui.resetandroidapp.Fragments.Quiz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.appolica.flubber.Flubber;
import com.walidhelaoui.resetandroidapp.Fragments.Quiz.DrinkingQuiz.QuizA1Fragment;
import com.walidhelaoui.resetandroidapp.Fragments.Quiz.SmokingQuiz.QuizS1Fragment;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoixFragment extends Fragment {

    private ImageView cig,alchool,arrow1,arrow2;

    public ChoixFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choix, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cig = (ImageView) view.findViewById(R.id.cigarette);
        alchool = (ImageView) view.findViewById(R.id.alchool);
        arrow1 = (ImageView) view.findViewById(R.id.quizArrow1);
        arrow2 = (ImageView) view.findViewById(R.id.quizArrow2);

        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_RIGHT) // Slide up animation
                .repeatCount(Animation.INFINITE)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(arrow1)                             // Apply it to the view
                .start();                                    // Start it now

        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_LEFT) // Slide up animation
                .repeatCount(Animation.INFINITE)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(arrow2)                             // Apply it to the view
                .start();

        cig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(new QuizS1Fragment());
            }
        });

        alchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(new QuizA1Fragment());
            }
        });


    }


}
