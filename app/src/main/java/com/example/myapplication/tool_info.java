package com.example.myapplication;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */

    public class tool_info extends Fragment {

    ArrayList<ToolInfo>info;
    ListView listView;
    private static SearchToolAdapter adapter;
    private  String barcode_id;
    private SharedPreferences.Editor mEditor;

        private String apilink;
        View view;
        ListView lv;
        List<ToolInfo> data ;
        ArrayList<ToolInfo> toolInfoList;
        SharedPreferences spf;


        Button btn_save;
        TextView barcode,status,specification,category,process,date,name,cycle,manufacturer,rack,row,compartment ;

        public static tool_info newInstance() {
            // Required empty public constructor
            tool_info Fragment =  new tool_info();
            return Fragment;
        }

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_testing, container, false);
//
//        listView = view.findViewById(R.id.listview);
//        adapter=new SearchToolAdapter(getContext(),info);
//
//        listView.setAdapter(adapter);
//
//        return view;
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_tool_info, container, false);
        apilink = getString(R.string.api);

//        btn_save = view.findViewById(R.id.button_save);
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


        spf = getContext().getSharedPreferences("barcodeshare", MODE_PRIVATE);
        barcode_id = spf.getString("barcode",null);

        if(barcode_id!=null){
            GetValidtool();
        }



//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();
//            }
//        });
        return  view;
    }



    private void GetValidtool(){

//        spf = getContext().getSharedPreferences("barcodeshare", MODE_PRIVATE);
//        String barcode_id = spf.getString("barcode",null);

//        if(barcode_id == null){
//            onStop();
//        }else{}


        final String data ="/api/tmsDev?searchdata={\"barcodeid\":\"" + barcode_id + "\"}";

//        final String data ="/api/tms?toolsearchdata={\"barcodeid\":\"BMA5015025130001\"}";
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
//        \"Section\":\"" + compartment.getText().toString() + "\" }";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();



        Call<String> call = retrofit.create(retro.GetValidtool.class).getvalidinfo(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();

                if (response.isSuccessful()) {

//                    String valid = response.body();
                    String jsonResponse = response.body();

//                    Toast.makeText(getContext(), "Retrofit success", Toast.LENGTH_SHORT).show();

                    try {
                        JSONObject object = new JSONObject(jsonResponse);
                        toolInfoList = new ArrayList<ToolInfo>();
//                            data = new ArrayList<>(Arrays.asList((jsonResponse.getJoblists())));

                        JSONArray dataArray = object.getJSONArray("data");

                        for (int i = 0; i < dataArray.length(); i++) {
//                            Toast.makeText(getContext(), "jsrespone RETRIEVING", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();

                            JSONObject dataobj = dataArray.getJSONObject(i);

                            ToolInfo toolInfodata = new ToolInfo();
                            toolInfodata.setBarcode(dataobj.getString("BarcodeID"));
                            toolInfodata.setStatusid(dataobj.getString("Status"));
                            toolInfodata.setToolspec(dataobj.getString("Description"));
                            toolInfodata.setToolgroup(dataobj.getString("ToolCategory"));
                            toolInfodata.setProcessid(dataobj.getString("Process"));
                            toolInfodata.setLastpmdate(dataobj.getString("LastPmDate"));
                            toolInfodata.setBarcodename(dataobj.getString("BarcodeName"));
                            toolInfodata.setVendorname(dataobj.getString("VendorName"));
                            toolInfodata.setPmid(dataobj.getString("PMCycle"));
                            toolInfodata.setRack(dataobj.getString("Rack"));
                            toolInfodata.setRow(dataobj.getString("Row"));
                            toolInfodata.setSection(dataobj.getString("Section"));



                            toolInfoList.add(toolInfodata);
                        }


                        for (int j = 0; j < toolInfoList.size(); j++) {
                            barcode.setText("Barcode ID : " + toolInfoList.get(j).getBarcode());
                            status.setText("Status : " +toolInfoList.get(j).getStatusid());
                            specification.setText("Description : " +toolInfoList.get(j).getToolspec());
                            category.setText("Tool Category : " +toolInfoList.get(j).getToolgroup());
                            process.setText("Process : " +toolInfoList.get(j).getProcessid());
                            date.setText("Last PM Date : " +toolInfoList.get(j).getLastpmdate());
                            name.setText("Barcode Name : " +toolInfoList.get(j).getBarcodename());
                            manufacturer.setText("Vendor Name : " +toolInfoList.get(j).getVendorname());
                            cycle.setText("PM Cycle : " +toolInfoList.get(j).getPmid());
                            rack.setText("Rack : " +toolInfoList.get(j).getRack());
                            row.setText("Row : " +toolInfoList.get(j).getRow());
                            compartment.setText("Section : " +toolInfoList.get(j).getSection());

//                                Toast.makeText(getContext(), "population", Toast.LENGTH_SHORT).show();

                        }

                        checkbarcodesharepreferences();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "jrespone fail", Toast.LENGTH_SHORT).show();
                }
            }
//        }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "TEST", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkbarcodesharepreferences(){
        mEditor = getContext().getSharedPreferences("barcodeshare", MODE_PRIVATE).edit();
        mEditor.putString("barcode", "");
        mEditor.commit();
    }

      }



//  private void GetValidtool(){
//
//        spf = getContext().getSharedPreferences("barcodeshare", MODE_PRIVATE);
//        String barcode_id = spf.getString("barcode",null);
//
//        final String data ="/api/tms?toolsearchdata={\"barcodeid\":\"" + barcode_id + "\"}";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://pngjvfa01")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//        Call<JsonResponse> call = retrofit.create(retro.GetInfo.class).getJson(data);
//        call.enqueue(new Callback<JsonResponse>() {
//            @Override
//            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
//
//                if (response.isSuccessful()) {
//                    JsonResponse jsonResponse = response.body();
//                    info = new ArrayList<ToolInfo>(Arrays.<ToolInfo>asList(jsonResponse.getInfolist()));
//                    lv = view.findViewById(R.id.listview);
////                    lv.setAdapter(new SearchToolAdapter(data, getActivity()));
//
//
//
//                }
//                else {
//                    Toast.makeText(getContext(), "jrespone fail", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonResponse> call, Throwable t) {
//                Toast.makeText(getContext(), "TEST", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }



