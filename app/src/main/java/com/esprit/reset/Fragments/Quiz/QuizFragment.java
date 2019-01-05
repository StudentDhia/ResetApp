package com.esprit.reset.Fragments.Quiz;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appolica.flubber.Flubber;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.esprit.reset.MainActivity;
import com.esprit.reset.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    private TextView message;
    private ImageView kuma;

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        message = (TextView) view.findViewById(R.id.msgQuiz);
        kuma = (ImageView) view.findViewById(R.id.Quiz0kuma);

        YoYo.with(Techniques.FlipInX)
                .duration(1000)
                .playOn(message);

        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_UP) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(kuma)                             // Apply it to the view
                .start();                                    // Start it now


        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                message.setText("Let's get started with a quiz !");

                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        ((MainActivity)getActivity()).replaceFragment(new ChoixFragment());
                    }
                };

                handler.postDelayed(runnable, 3000);
            }
        };

        handler.postDelayed(runnable, 3000);



    }

}
