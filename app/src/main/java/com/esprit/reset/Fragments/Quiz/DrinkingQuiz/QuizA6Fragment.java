package com.esprit.reset.Fragments.Quiz.DrinkingQuiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.esprit.reset.MainActivity;
import com.esprit.reset.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link QuizA6Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizA6Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private Button btnNext,btnNext2,btnNext3,btnNext4,btnNext5;

    public QuizA6Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment QuizA6Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizA6Fragment newInstance(int param1) {
        QuizA6Fragment fragment = new QuizA6Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_a6, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnNext = (Button) view.findViewById(R.id.a6_reponse1);
        btnNext2 = (Button) view.findViewById(R.id.a6_reponse2);
        btnNext3 = (Button) view.findViewById(R.id.a6_reponse3);
        btnNext4 = (Button) view.findViewById(R.id.a6_reponse4);
        btnNext5 = (Button) view.findViewById(R.id.a6_reponse5);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizA7Fragment.newInstance(0));
            }
        });

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizA7Fragment.newInstance(1));
            }
        });

        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizA7Fragment.newInstance(2));
            }
        });

        btnNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizA7Fragment.newInstance(3));
            }
        });

        btnNext5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizA7Fragment.newInstance(4));
            }
        });

    }

}
