package com.walidhelaoui.resetandroidapp.Fragments.Quiz.DailySmokingQuiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.walidhelaoui.resetandroidapp.MainActivity;
import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DailyQuizS4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyQuizS4Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArcProgress arc1,arc2;
    private TextView days, infos;
    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;


    public DailyQuizS4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyQuizS4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyQuizS4Fragment newInstance(int param1, int param2) {
        DailyQuizS4Fragment fragment = new DailyQuizS4Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_quiz_s4, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        infos = (TextView) view.findViewById(R.id.msgLungs);
        arc1 = (ArcProgress) view.findViewById(R.id.arc_progress);
        arc2 = (ArcProgress) view.findViewById(R.id.arc_progress2);
        days = (TextView) view.findViewById(R.id.daysLungs);

        wastedDays(220);

        infos.setText("Hey there !");

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(arc1.getProgress() > 15){
                    infos.setText("The nicotine level in your blood is getting too high. " +
                            "Let's stop smoking now. ou're not planning to die now, right ?");
                }

                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (arc2.getProgress() > 5){
                            infos.setText("You lungs are at "+ arc2.getProgress() + ". Let's not taint your lungs and keep them clean.");
                        }

                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {

                                infos.setText("You wasted " + days.getText());

                                Handler handler = new Handler();
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                       // infos.setText("You wasted " + days.getText());
                                        startActivity(new Intent(getActivity(), MainActivity.class));
                                    }
                                };

                                handler.postDelayed(runnable, 3000);
                            }
                        };

                        handler.postDelayed(runnable, 3000);
                    }
                };

                handler.postDelayed(runnable, 3000);
            }
        };

        handler.postDelayed(runnable, 3000);


    }


    public void wastedDays(int cigs){

        days.setText(((cigs * 7)/1440) + " Days");
    }
}
