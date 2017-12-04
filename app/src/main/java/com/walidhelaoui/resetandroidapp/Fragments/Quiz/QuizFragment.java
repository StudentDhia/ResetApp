package com.walidhelaoui.resetandroidapp.Fragments.Quiz;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    private TextView message;
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

        YoYo.with(Techniques.FlipInX)
                .duration(1000)
                .playOn(message);


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
