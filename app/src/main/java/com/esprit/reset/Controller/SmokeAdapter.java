package com.esprit.reset.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.esprit.reset.Entity.SmokingStatistics;
import com.esprit.reset.R;

import java.util.List;

/**
 * Created by walid on 27/11/2017.
 */

public class SmokeAdapter  extends ArrayAdapter<SmokingStatistics> {

    Context ctx;

    public static class ViewHolder{
        TextView txtNumber;
        TextView txtDate;
        TextView txtPrice;
    }

    public SmokeAdapter(@NonNull Context context, @NonNull List<SmokingStatistics> smokes) {
        super(context, 0, smokes);
        this.ctx = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SmokingStatistics smoke = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_smoking_statistics,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.txtNumber);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtNumber.setText(String.valueOf(smoke.getNumber()));
        viewHolder.txtDate.setText(smoke.getDate());
        viewHolder.txtPrice.setText(String.valueOf(smoke.getPrice()));

        return  convertView;
    }
}
