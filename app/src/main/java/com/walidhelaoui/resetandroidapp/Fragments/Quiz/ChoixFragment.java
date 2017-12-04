package com.walidhelaoui.resetandroidapp.Fragments.Quiz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoixFragment extends Fragment {

    private ImageView cig,alchool;

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

        cig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(new QuizFragment2());
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
