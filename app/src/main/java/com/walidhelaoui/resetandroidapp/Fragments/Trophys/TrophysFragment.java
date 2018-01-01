package com.walidhelaoui.resetandroidapp.Fragments.Trophys;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.walidhelaoui.resetandroidapp.R;
import com.walidhelaoui.resetandroidapp.Services.NotificationService;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrophysFragment extends Fragment {

    private TextView text;

    public TrophysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trophys, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text = (TextView) view.findViewById(R.id.textTrophee);

        ImageView t1 = (ImageView) view.findViewById(R.id.t1);
        ImageView t2 = (ImageView) view.findViewById(R.id.t2);
        ImageView t3 = (ImageView) view.findViewById(R.id.t3);
        ImageView t4 = (ImageView) view.findViewById(R.id.t4);
        ImageView t5 = (ImageView) view.findViewById(R.id.t5);
        ImageView t6 = (ImageView) view.findViewById(R.id.t6);
        ImageView t7 = (ImageView) view.findViewById(R.id.t7);
        ImageView t8 = (ImageView) view.findViewById(R.id.t8);
        ImageView t9 = (ImageView) view.findViewById(R.id.t9);
        ImageView t10 = (ImageView) view.findViewById(R.id.t10);
        ImageView t11 = (ImageView) view.findViewById(R.id.t11);
        ImageView t12 = (ImageView) view.findViewById(R.id.t12);

        comparaisonTrophy3(4,1,t1,"Trophy : You didn't smoke for 1 day");
        comparaisonTrophy2(4,3,t2, "Trophy : You didn't smoke for 3 day");
        comparaisonTrophy(4,7,t3, "Trophy : You didn't smoke for 7 day");

        comparaisonTrophy3(25,20,t4, "Trophy : You saved 20$ from smoking");
        comparaisonTrophy2(25,50,t5, "Trophy : You saved 50$ from smoking");
        comparaisonTrophy(25,100,t6, "Trophy : You saved 100$ from smoking");

        comparaisonTrophy3(1,1,t7, "Trophy : You didn't drink for 1 day");
        comparaisonTrophy2(4,3,t8, "Trophy : You didn't smoke for 3 day");
        comparaisonTrophy(4,7,t9, "Trophy : You didn't smoke for 1 week");

        comparaisonTrophy3(14,20,t10, "Trophy : You saved 20$ from alcool");
        comparaisonTrophy2(14,50,t11, "Trophy : You saved 50$ from alcool");
        comparaisonTrophy(14,100,t12, "Trophy : You saved 100$ from alcool");

    }

    // VAL = VALUE FROM DB
    // LIMITE = CONDITION FROM THE TROPHY
    // IMG = THE TROPHY IMAGE
    public void comparaisonTrophy(int val, int limite, ImageView img, final String msg){

        if(val >= limite){

            img.setImageResource(R.drawable.trophy);
            NotificationService ns = new NotificationService();
            //ns.createNotificationImage(msg,R.drawable.trophy, getActivity().getIntent());
            //FAUT CHANGER CA POUR QUE LA NOTIFICATION SE LANCE A TOUT MOMENT
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(msg);
            }
        });
    }

    public void comparaisonTrophy2(int val, int limite, ImageView img, final String msg){

        if(val >= limite){

            img.setImageResource(R.drawable.argent);
            NotificationService ns = new NotificationService();
            //ns.createNotificationImage(msg, R.drawable.argent, getActivity().getIntent());
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(msg);
            }
        });
    }

    public void comparaisonTrophy3(int val, int limite, ImageView img, final String msg){

        if(val >= limite){

            img.setImageResource(R.drawable.bronze);
            NotificationService ns = new NotificationService();
           // ns.createNotificationImage(msg, R.drawable.bronze,getActivity().getIntent());
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(msg);
            }
        });
    }

}
