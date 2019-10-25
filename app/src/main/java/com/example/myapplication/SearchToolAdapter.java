package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SearchToolAdapter extends ArrayAdapter<ToolInfo> {

  private ArrayList<ToolInfo> toolinfo;
  Context mContext;

    private static class Viewholder{
        TextView barcode,status,specification,category,process,date,name,cycle,manufacturer,rack,row,compartment;
    }

    public SearchToolAdapter(Context context,ArrayList<ToolInfo> toolinfo) {
        super(context, R.layout.itemrowlayout,toolinfo);
        this.toolinfo = toolinfo;
        this.mContext=context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Viewholder holder;

        ToolInfo toolInfo_item = getItem(position);

        holder = new Viewholder();
//        LayoutInflater inflater = LayoutInflater.from(getContext());
        holder.barcode = convertView.findViewById(R.id.barcodeinfo);
        holder.status = convertView.findViewById(R.id.statusinfo);
        holder.specification = convertView.findViewById(R.id.specinfo);
        holder.category = convertView.findViewById(R.id.categoryinfo);
        holder.process = convertView.findViewById(R.id.processinfo);
        holder.date = convertView.findViewById(R.id.pmdateinfo);
        holder.name = convertView.findViewById(R.id.bnameinfo);
        holder.manufacturer = convertView.findViewById(R.id.manufacturerinfo);
        holder.cycle = convertView.findViewById(R.id.pmcycleinfo);
        holder.rack = convertView.findViewById(R.id.rackinfo);
        holder.row = convertView.findViewById(R.id.rowinfo);
        holder.compartment = convertView.findViewById(R.id.compartmentinfo);
        convertView.setTag(holder);


        holder.barcode.setText(toolInfo_item.getBarcode());
        holder.status.setText(toolInfo_item.getStatusid());
        holder.specification.setText(toolInfo_item.getToolspec());
        holder.category.setText(toolInfo_item.getToolgroup());
        holder.process.setText(toolInfo_item.getProcessid());
        holder.date.setText(toolInfo_item.getLastpmdate());
        holder.name.setText(toolInfo_item.getBarcodename());
        holder.manufacturer.setText(toolInfo_item.getVendorname());
        holder.cycle.setText(toolInfo_item.getPmid());
        holder.rack.setText(toolInfo_item.getRack());
        holder.row.setText(toolInfo_item.getRow());
        holder.compartment.setText(toolInfo_item.getSection());


        return getView(position, convertView, parent);
    }

}
