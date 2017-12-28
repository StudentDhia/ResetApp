package com.walidhelaoui.resetandroidapp.Fragments.Quiz.DailySmokingQuiz;

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
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the* to handle interaction events.
 * Use the {@link DailyQuizS3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyQuizS3Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private EditText et;
    public DailyQuizS3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DailyQuizS3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyQuizS3Fragment newInstance(int param1) {
        DailyQuizS3Fragment fragment = new DailyQuizS3Fragment();
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
        return inflater.inflate(R.layout.fragment_daily_quiz_s3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et = (EditText) view.findViewById(R.id.m3_reponse1);
        Button next = (Button) view.findViewById(R.id.m3_next);
        ImageView kuma = (ImageView) view.findViewById(R.id.dailyKuma3);


        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_UP) // Slide up animation
                .repeatCount(1)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(view)                             // Apply it to the view
                .start();                                    // Start it now

        // Added the cigarette's price

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(et.getText().toString());
                ((MainActivity)getActivity()).replaceFragment(DailyQuizS4Fragment.newInstance(mParam1,number));
            }
        });
    }
}
