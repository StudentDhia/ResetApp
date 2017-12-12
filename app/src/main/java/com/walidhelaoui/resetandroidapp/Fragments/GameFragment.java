package com.walidhelaoui.resetandroidapp.Fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.walidhelaoui.resetandroidapp.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    Button startButton,button1,button2,button3,button4,playAgain;
    TextView sumTextView,resulatTextView,pointsTextView,timerTextView;
    RelativeLayout game;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        startButton = (Button) view.findViewById(R.id.startButton);
        sumTextView = (TextView) view.findViewById(R.id.sumTextView);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        resulatTextView = (TextView) view.findViewById(R.id.resulatTextView);
        pointsTextView = (TextView) view.findViewById(R.id.PointsTextView);
        timerTextView = (TextView) view.findViewById(R.id.TimerTextView);
        playAgain = (Button) view.findViewById(R.id.PalyAgainButton);
        game = (RelativeLayout) view.findViewById(R.id.gameRelativeLayout);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startButton.setVisibility(View.INVISIBLE);
                game.setVisibility(View.VISIBLE);
                playAgain();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(view);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(view);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(view);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(view);
            }
        });

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
            }
        });

        return view;
    }

    public void generateQuestions(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b) );

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            }else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }


    public void chooseAnswer(View view) {
        Log.i("TAG",(String) view.getTag());
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resulatTextView.setText("Correct :D");
        }else {
            resulatTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestions();
    }

    public void playAgain() {
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resulatTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        generateQuestions();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resulatTextView.setText("Your score : "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();


    }

}
