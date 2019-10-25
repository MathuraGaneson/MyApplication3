package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public class CustomListView extends ArrayAdapter<String> {

    private String[] information;
    private Activity context;

    public CustomListView(@NonNull Activity context, String[] information) {
        super(context, R.layout.fragment_testing,information);

        this.context=context;
        this.information=information;
    }

    public View getView(int position,View convertView, ViewGroup parent){

        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){

            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.fragment_testing,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }

        else{
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.info.setText(information[position]);


        return r;
    }

    class ViewHolder
    {
        TextView info;
        ViewHolder(View v)
        {
//            info=v.findViewById(R.id.listview);
        }
    }
}
