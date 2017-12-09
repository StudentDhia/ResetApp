package com.walidhelaoui.resetandroidapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.walidhelaoui.resetandroidapp.Controller.MissionsAdapter;
import com.walidhelaoui.resetandroidapp.Entity.Mission;
import com.walidhelaoui.resetandroidapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MissionsFragment extends Fragment {

    private ListView listMissions;
    List<Mission> missions = new ArrayList<>();
    private TextView missionText;
    private String[] desc_missions = {"Take 10 deep breaths, walk to the sink, pour yourself a glass of ice water, and drink it slowly."
            , "Keep your mouth busy. Chew a stick of gum instead of picking up a cigarette. Keep hard candy with you. Drink more water."
            , "Go for a walk or jog. Or go up and down the stairs a few times. Physical activity, even in short bursts, can help boost your energy and beat a craving."
            , "Call a friend or a family member who supports your efforts to quit smoking. You don’t have to talk to them about smoking or quitting -- just hold the phone in your hand instead of a cigarette, and talk about sports, the weather, or your next vacation until the craving passes."
            , "Play a game. Whether it's a board game with your child or a game outside with your dog, games are great distractions. Playing outdoors with Fido is good exercise and gets both of you some fresh air. Playing with your child will remind you of why it's so important to quit smoking."
            , "Take a Warm Bath\n" +
            "This is one of my favorite ways to relax and de-stress. I recommend it often, and yes, it's good for the guys too. Light a few candles, use some scented bath salts, and submerge"};

    private Button missionDone;
    public MissionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_missions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        missionText = (TextView) view.findViewById(R.id.main_textMission);
        missionDone = (Button) view.findViewById(R.id.mission_done);

        missions.add(new Mission("Mission Breaths", "", false));
        missions.add(new Mission("Mission Mouth", "", false));
        missions.add(new Mission("Mission Running", "", false));
        missions.add(new Mission("Mission Friend", "", false));
        missions.add(new Mission("Mission Game", "", false));
        missions.add(new Mission("Mission Bath", "", false));

        listMissions = (ListView) view.findViewById(R.id.list_missions);

        final MissionsAdapter missionsAdapter = new MissionsAdapter(getActivity(),missions);
        listMissions.setAdapter(missionsAdapter);

        listMissions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {

                missionText.setText(desc_missions[i]);

                missionDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        missions.get(i).setEtat(true);
                        missionsAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}
