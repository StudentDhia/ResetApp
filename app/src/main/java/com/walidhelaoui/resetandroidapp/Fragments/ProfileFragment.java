package com.walidhelaoui.resetandroidapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;
import com.walidhelaoui.resetandroidapp.R;
import com.walidhelaoui.resetandroidapp.utils.CurrentUser;

import cn.fanrunqi.waveprogress.WaveProgressView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    int progressMax1,progressMax2;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WaveProgressView waveProgressbar = (WaveProgressView) view.findViewById(R.id.waveProgressbar2);
        WaveProgressView waveProgressbar2 = (WaveProgressView) view.findViewById(R.id.waveProgressbar);
        TextView textView1 = (TextView) view.findViewById(R.id.textDependance2);
        TextView textView2 = (TextView) view.findViewById(R.id.textDependance1);
        TextView textView3 = (TextView) view.findViewById(R.id.txtEmail);

        textView1.setTypeface(EasyFonts.caviarDreamsBold(getContext()));
        textView2.setTypeface(EasyFonts.caviarDreamsBold(getContext()));
        textView3.setTypeface(EasyFonts.caviarDreamsBold(getContext()));

        float smoke_score = CurrentUser.user.getSmoke_score();
        float drink_score  = CurrentUser.user.getDrink_score();
        progressMax1 = 8;
        progressMax2 = 26;


        if (smoke_score < 3) {
            textView1.setText("Smoke : Low dependance");

        } else if (smoke_score == 3 || smoke_score == 4) {
            textView1.setText("Smoke : Low to mod dependence");
        } else if (smoke_score == 5 || smoke_score == 6) {
            textView1.setText("Smoke : Moderate dependence");
        } else {
            textView1.setText("Smoke : High dependence");
        }


        if (drink_score < 12) {
            textView2.setText("Alcohol : Low dependance");
        } else if (drink_score <16 && drink_score > 11) {
            textView2.setText("Alcohol : Low to mod dependence");
        } else if (drink_score <22 && drink_score > 15) {
            textView2.setText("Alcohol : Moderate dependence");
        } else {
            textView2.setText("Alcohol : High dependence");
        }

        waveProgressbar.setCurrent((int)smoke_score,""); // 77, "788M/1024M"
        waveProgressbar.setMaxProgress(progressMax1);
        waveProgressbar.setWaveColor("#3d3d29"); //"#5b9ef4"
        waveProgressbar.setWave(20,100);
        waveProgressbar.setmWaveSpeed(50);//The larger the value, the slower the vibration

        waveProgressbar2.setCurrent((int)drink_score,""); // 77, "788M/1024M"
        waveProgressbar2.setMaxProgress(progressMax2);
        waveProgressbar2.setWaveColor("#3d3d29"); //"#5b9ef4"
        waveProgressbar2.setWave(20,100);
        waveProgressbar2.setmWaveSpeed(50);//The larger the value, the slower the vibration
    }

}
