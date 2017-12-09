package com.walidhelaoui.resetandroidapp.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.walidhelaoui.resetandroidapp.Entity.Mission;
import com.walidhelaoui.resetandroidapp.R;

import java.util.List;

/**
 * Created by walid on 09/12/2017.
 */

public class MissionsAdapter extends ArrayAdapter<Mission> {

static class ViewHolder{
    ImageView imageView;
    TextView textMission;
    TextView text2Mission;
}

    public MissionsAdapter(@NonNull Context context, List<Mission> missionList) {
        super(context,0, missionList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Mission mission = getItem(position);
        ViewHolder vh;
        if (convertView == null) {
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_mission,parent, false);
            vh = new ViewHolder();
            vh.imageView = (ImageView) convertView.findViewById(R.id.image_row);
            vh.textMission = (TextView) convertView.findViewById(R.id.text_row);
            vh.text2Mission = (TextView) convertView.findViewById(R.id.text2_row);
            convertView.setTag(vh);

        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.imageView.setImageResource(R.drawable.iconstar);
        vh.textMission.setText(mission.getLibelle());

        if(mission.getEtat()){
            vh.text2Mission.setText("Completed");
        }else{
            vh.text2Mission.setText("In progress");
        }

        return convertView;
    }
}
