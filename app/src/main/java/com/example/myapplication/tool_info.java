package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */






    public class tool_info extends Fragment {

        private String apilink;
        View view;
        List<ToolInfo> toolInfoList ;


        Button btn_save;
        EditText barcode,status,specification,category,process,date,name,cycle,manufacturer,rack,row,compartment ;

        public static tool_info newInstance() {
            // Required empty public constructor
            tool_info Fragment =  new tool_info();
            return Fragment;
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_tool_info, container, false);
        apilink = getString(R.string.api);

        btn_save = view.findViewById(R.id.button_save);
        barcode = view.findViewById(R.id.barcode_id);

        status = view.findViewById(R.id.tool_status);
        specification = view.findViewById(R.id.spec);
        category = view.findViewById(R.id.category);
        process = view.findViewById(R.id.Process);
        date = view.findViewById(R.id.date);
        name = view.findViewById(R.id.barcode_name);
        manufacturer = view.findViewById(R.id.Manufacturer);
        cycle = view.findViewById(R.id.pm_cycle);
        rack = view.findViewById(R.id.rack);
        row = view.findViewById(R.id.row);
        compartment = view.findViewById(R.id.compartment);



        GetValidtool();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
    }

    private void GetValidtool(){
        final String data ="/api/tms?toolsearchdata={\"barcodeid\":\"" + barcode.getText().toString() + "\"}";

//        \"StatusID\":\"" + status.getText().toString() + "\"
//        \"ToolSpecID\":\"" + specification.getText().toString() + "\"
//        \"Toolgroupid\":\"" + category.getText().toString() + "\"
//        \"ProcessId\":\"" + process.getText().toString() + "\"
//        \"LastPMDate\":\"" + date.getText().toString() + "\"
//        \"Name\":\"" + name.getText().toString() + "\"
//        \"Name\":\"" + manufacturer.getText().toString() + "\"
//        \"PMID\":\"" + cycle.getText().toString() + "\"
//        \"Rack\":\"" + rack.getText().toString() + "\"
//        \"Row\":\"" + row.getText().toString() + "\"
//        \"Section\":\"" + compartment.getText().toString() + "\"

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(retro.GetValidtool.class).getvalidinfo(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
//                    String valid = response.body();
                        String jsonResponse = response.body();

//                    Toast.makeText(getContext(), "Retrofit success", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), jsonResponse, Toast.LENGTH_SHORT).show();

                    if (jsonResponse.equals("\"true\"")){

//                        Toast.makeText(getContext(), "jsrespone condition", Toast.LENGTH_SHORT).show();
                        try{
                            JSONObject object = new JSONObject(jsonResponse);
                            toolInfoList = new  ArrayList<>();
//                            data = new ArrayList<>(Arrays.asList((jsonResponse.getJoblists())));
                            JSONArray dataArray = object.getJSONArray("data");

                            for (int i = 0 ; i<dataArray.length(); i++)
                            {
                                ToolInfo toolInfodata = new ToolInfo();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                toolInfodata.setBarcode(dataobj.getString("Barcode"));
                                toolInfodata.setStatusid(dataobj.getString("Status"));
                                toolInfodata.setToolspec(dataobj.getString("Specification"));
                                toolInfodata.setToolgroup(dataobj.getString("Category"));
                                toolInfodata.setProcessid(dataobj.getString("Process ID"));
                                toolInfodata.setLastpmdate(dataobj.getString("Last PM Date"));
                                toolInfodata.setBarcodename(dataobj.getString("Manufacturer Name"));
                                toolInfodata.setVendorname(dataobj.getString("Vendor Name"));
                                toolInfodata.setPmid(dataobj.getString("PM Id"));
                                toolInfodata.setRack(dataobj.getString("Rack"));
                                toolInfodata.setRow(dataobj.getString("Row"));
                                toolInfodata.setSection(dataobj.getString("Section"));

//                                Toast.makeText(getContext(), "retrieving", Toast.LENGTH_SHORT).show();

                                toolInfoList.add(toolInfodata);
                            }


                            for (int j=0; j<toolInfoList.size();j++ )
                            {
                                barcode.setText(toolInfoList.get(j).getBarcode());
                                status.setText(toolInfoList.get(j).getStatusid());
                                specification.setText(toolInfoList.get(j).getToolspec());
                                category.setText(toolInfoList.get(j).getToolgroup());
                                process.setText(toolInfoList.get(j).getProcessid());
                                date.setText(toolInfoList.get(j).getLastpmdate());
                                name.setText(toolInfoList.get(j).getBarcodename());
                                manufacturer.setText(toolInfoList.get(j).getVendorname());
                                cycle.setText(toolInfoList.get(j).getPmid());
                                rack.setText(toolInfoList.get(j).getRack());
                                row.setText(toolInfoList.get(j).getRow());
                                compartment.setText(toolInfoList.get(j).getSection());

//                                Toast.makeText(getContext(), "population", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                } else{
                        Toast.makeText(getContext(), "jrespone fail", Toast.LENGTH_SHORT).show();
                }
            }}

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "TEST", Toast.LENGTH_SHORT).show();
            }
        });

    }


//    public class JsonResponse{
//            private JobAvailableClass[] joblists;
//
//        public JobAvailableClass[] getJoblists() {
//            return joblists;
//        }
//    }

}
