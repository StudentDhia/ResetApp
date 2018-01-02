package com.walidhelaoui.resetandroidapp.Fragments.Quiz.DailyDrinkingQuiz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.appolica.flubber.Flubber;
import com.walidhelaoui.resetandroidapp.Fragments.Quiz.DailySmokingQuiz.DailyQuizS3Fragment;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyQuizA2Fragment extends Fragment {

    private EditText et;

    public DailyQuizA2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_quiz_a2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et = (EditText) view.findViewById(R.id.m2_reponse1);
        Button next = (Button) view.findViewById(R.id.m2_next);
        ImageView kuma = (ImageView) view.findViewById(R.id.dailyKuma2);

        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_UP) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(kuma)                             // Apply it to the view
                .start();                                    // Start it now

        // Added the cigarette's number

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(et.getText().toString());
                ((MainActivity)getActivity()).replaceFragment(DailyQuizS3Fragment.newInstance(number));
            }
        });
    }

}
