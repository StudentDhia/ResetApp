package com.walidhelaoui.resetandroidapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.walidhelaoui.resetandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    EditText smokingPrice,drinkingPrice;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

         smokingPrice = (EditText) view.findViewById(R.id.edtSmokingPrice);
         drinkingPrice = (EditText) view.findViewById(R.id.edtDrinkingPrice);

        Button saveChanges = (Button) view.findViewById(R.id.btn_setting);

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });



        return view;
    }

    public boolean validate() {
        boolean valid = true;

        String smokingPrice = (this.smokingPrice.getText().toString());
        String drinkingPrice = (this.drinkingPrice.getText().toString());

        try {
            Float.valueOf(smokingPrice);
        }catch (NumberFormatException e){
            this.smokingPrice.setError("not a valid number");
            valid = false;
        }

        try {
            Float.valueOf(drinkingPrice);
        }catch (NumberFormatException e){
            this.drinkingPrice.setError("not a valid number");
            valid = false;
        }

        return valid;
    }

}
