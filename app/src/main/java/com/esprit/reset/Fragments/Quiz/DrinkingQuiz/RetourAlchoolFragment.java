package com.esprit.reset.Fragments.Quiz.DrinkingQuiz;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.esprit.reset.Fragments.Quiz.SmokingQuiz.QuizS1Fragment;
import com.esprit.reset.MainActivity;
import com.esprit.reset.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetourAlchoolFragment extends Fragment {


    private Button retour1,retour2;

    public RetourAlchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_retour_alchool, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retour1 = (Button) view.findViewById(R.id.retour1_reponse2);
        retour2 = (Button) view.findViewById(R.id.retour2_reponse2);

        retour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(new QuizS1Fragment());
            }
        });

        retour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((MainActivity)getActivity()).replaceFragment(new SmokeFragment(getActivity()));
                startActivity(new Intent(getActivity(),MainActivity.class));

            }
        });
    }

}
