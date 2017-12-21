package com.walidhelaoui.resetandroidapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walidhelaoui.resetandroidapp.R;
import com.walidhelaoui.resetandroidapp.utils.CurrentUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView etatTxt = (TextView) view.findViewById(R.id.txtEtat);


            // prefs.edit().putBoolean(PREFS_QUIZ_SMOKING, true).apply();
            if (CurrentUser.user.getSmoke_score() < 3) {
                etatTxt.setText(etatTxt.getText()+ "Cigarette : Low dependance");
            } else if (CurrentUser.user.getSmoke_score() == 3 || CurrentUser.user.getSmoke_score() == 4) {
                etatTxt.setText(etatTxt.getText()+ "Cigarette : Low to mod dependence");
            } else if (CurrentUser.user.getSmoke_score() == 5 || CurrentUser.user.getSmoke_score() == 6) {
                etatTxt.setText(etatTxt.getText()+ "Cigarette : Moderate dependence");
            } else {
                etatTxt.setText(etatTxt.getText()+ "Cigarette : High dependence");
            }

            if (CurrentUser.user.getDrink_score() < 12) {
                etatTxt.setText(etatTxt.getText()+" Alcohol : Low dependance");
            } else if (CurrentUser.user.getDrink_score() <16 && CurrentUser.user.getDrink_score() > 11) {
                etatTxt.setText(etatTxt.getText()+" Alcohol : Low to mod dependence");
            } else if (CurrentUser.user.getDrink_score() <22 && CurrentUser.user.getDrink_score() > 15) {
                etatTxt.setText(etatTxt.getText()+" Alcohol : Moderate dependence");
            } else {
                etatTxt.setText(etatTxt.getText()+" Alcohol : High dependence");
            }

        return view;
    }

}
