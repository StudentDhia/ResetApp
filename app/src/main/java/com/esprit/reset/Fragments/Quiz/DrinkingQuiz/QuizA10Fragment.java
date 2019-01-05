package com.esprit.reset.Fragments.Quiz.DrinkingQuiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.esprit.reset.Fragments.Quiz.ScoreFragment;
import com.esprit.reset.MainActivity;
import com.esprit.reset.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link QuizA10Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizA10Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private Button btnNext,btnNext2,btnNext3;


    public QuizA10Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment QuizA10Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizA10Fragment newInstance(int param1) {
        QuizA10Fragment fragment = new QuizA10Fragment();
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
        return inflater.inflate(R.layout.fragment_quiz_a10, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnNext = (Button) view.findViewById(R.id.a10_reponse1);
        btnNext2 = (Button) view.findViewById(R.id.a10_reponse2);
        btnNext3 = (Button) view.findViewById(R.id.a10_reponse3);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(ScoreFragment.newInstance(0, "alcohol"));
            }
        });

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(ScoreFragment.newInstance(2, "alcohol"));
            }
        });

        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(ScoreFragment.newInstance(4, "alcohol"));
            }
        });

    }
}
