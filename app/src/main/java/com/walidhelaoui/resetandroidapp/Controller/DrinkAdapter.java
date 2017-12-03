package com.walidhelaoui.resetandroidapp.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.walidhelaoui.resetandroidapp.Entity.DrinkingStatistics;
import com.walidhelaoui.resetandroidapp.Entity.SmokingStatistics;
import com.walidhelaoui.resetandroidapp.R;

import java.util.List;

/**
 * Created by walid on 28/11/2017.
 */

public class DrinkAdapter  extends ArrayAdapter<DrinkingStatistics> {

    Context ctx;

    public static class ViewHolder{
        TextView txtNumber;
        TextView txtDate;
        TextView txtPrice;
    }

    public DrinkAdapter(@NonNull Context context, @NonNull List<DrinkingStatistics> drinks) {
        super(context, 0, drinks);
        this.ctx = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DrinkingStatistics drink = getItem(position);
        DrinkAdapter.ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_drinking_statistics,parent,false);
            viewHolder = new DrinkAdapter.ViewHolder();
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.txtNumber);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (DrinkAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.txtNumber.setText(String.valueOf(drink.getNumber()));
        viewHolder.txtDate.setText(drink.getDate());
        viewHolder.txtPrice.setText(String.valueOf(drink.getPrice()));

        return  convertView;
    }
}
