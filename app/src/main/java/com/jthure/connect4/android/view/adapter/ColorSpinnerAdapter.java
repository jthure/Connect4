package com.jthure.connect4.android.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jthure.connect4.R;
import com.jthure.connect4.android.util.ColorUtil;
import com.jthure.connect4.model.Color;

/**
 * Created by Jonas on 2016-11-24.
 */

public class ColorSpinnerAdapter extends BaseAdapter {
    private Color[] values;
    private Context context;
    public ColorSpinnerAdapter(Context context){
        this.context=context;

        values = new Color[Color.values().length-1];
        for(int i=0,j=0;i<values.length;i++){
            if(Color.values()[i]==Color.EMPTY) {
                j++;
            }
            values[i] = Color.values()[j];
            j++;
        }
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return values[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_spinner_color_select_item,null);
        ((TextView)v.findViewById(R.id.tv_color)).setText(values[i].toString());
        ((ImageView)v.findViewById(R.id.iv_color)).setImageResource(ColorUtil.getImageResource(values[i]));
        return v;
    }
}
