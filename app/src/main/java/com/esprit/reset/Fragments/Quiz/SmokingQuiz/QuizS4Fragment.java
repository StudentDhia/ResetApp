package com.esprit.reset.Fragments.Quiz.SmokingQuiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.esprit.reset.MainActivity;
import com.esprit.reset.R;

import static com.daimajia.androidanimations.library.BaseViewAnimator.DURATION;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link QuizS4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizS4Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private Button btnNext,btnNext2,btnNext3,btnNext4;

    public QuizS4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment QuizS4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizS4Fragment newInstance(int param1) {
        QuizS4Fragment fragment = new QuizS4Fragment();
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
        return inflater.inflate(R.layout.fragment_quiz_s4, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnNext = (Button) view.findViewById(R.id.q5_reponse1);
        btnNext2 = (Button) view.findViewById(R.id.q5_reponse2);
        btnNext3 = (Button) view.findViewById(R.id.q5_reponse3);
        btnNext4 = (Button) view.findViewById(R.id.q5_reponse4);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizS5Fragment.newInstance(mParam1));
            }
        });

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizS5Fragment.newInstance(mParam1 + 1));
            }
        });

        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizS5Fragment.newInstance(mParam1 + 2));
            }
        });

        btnNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).replaceFragment(QuizS5Fragment.newInstance(mParam1 + 3));
            }
        });

    }
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION);
    }
}
