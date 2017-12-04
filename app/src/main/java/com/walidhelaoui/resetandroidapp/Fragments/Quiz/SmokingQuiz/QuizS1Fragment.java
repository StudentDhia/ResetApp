package com.walidhelaoui.resetandroidapp.Fragments.Quiz.SmokingQuiz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizS1Fragment extends Fragment {

    private Button reponse1,reponse2,reponse3;

    public QuizS1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_s1, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reponse1 = (Button) view.findViewById(R.id.reponse1);
        reponse2 = (Button) view.findViewById(R.id.reponse2);
        reponse3 = (Button) view.findViewById(R.id.reponse3);


        reponse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(QuizS2Fragment.newInstance(3));
            }
        });

        reponse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(QuizS2Fragment.newInstance(2));
            }
        });

        reponse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(QuizS2Fragment.newInstance(1));
            }
        });

    }

}
