package com.walidhelaoui.resetandroidapp.Fragments.Quiz.DailySmokingQuiz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyQuizS2Fragment extends Fragment {


    public DailyQuizS2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_quiz_s2, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText et = (EditText) view.findViewById(R.id.m2_reponse1);
        Button next = (Button) view.findViewById(R.id.m2_next);

        // Added the cigarette's number
        final int number = Integer.parseInt(et.getText().toString());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(DailyQuizS3Fragment.newInstance(number));
            }
        });
    }

}

