package com.example.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.R.layout.simple_spinner_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class new_spec extends Fragment {

    View view;
    private Spinner spinner, spinner1, spinner2;
    private EditText tool_level;
    private Button spec_register;

    ArrayList<ToolInfo> infoArrayList;

    ArrayList<String> processList = new ArrayList<>();
    ArrayList<String> machineidList = new ArrayList<>();

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private String Process,Process2;

    public new_spec() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_spec, container, false);


        GetValidRegisterProcess();

        spinner = view.findViewById(R.id.spec_process);
        spinner1 = view.findViewById(R.id.spec_category);
        spinner2 = view.findViewById(R.id.spec_min);
        tool_level = view.findViewById(R.id.min_tool2);
        spec_register = view.findViewById(R.id.register_spec);


        tool_level.addTextChangedListener(textWatcher);
//        checkFieldsForEmptyValues();


        spec_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//           checkFieldsForEmptyValues();
                if(tool_level.getText().toString().trim().length()>0){
                GetNewSpec();
                tool_level.setText("");}

                else{
                    Toast.makeText(getContext(), "Tool Specification INVALID", Toast.LENGTH_LONG).show();
                }

            }

        });


        return view;



    }


    private void GetValidRegisterProcess(){
        String data = "/api/tmsDev?processList=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetRegisterProcess.class).getregisterprocess(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body());
//               Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()){
                    if(response.body() !=null){
                        Log.i("OnSuccess",response.body());

//                       Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                        String jsonresponse = response.body();
                        spinJson(jsonresponse);
                    }
                    else{
                        Log.i("onEmptyResponse", "Returned empty response");
                        Toast.makeText(getContext(),"fail",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void spinJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("info");

            for (int i = 0; i < dataArray.length(); i++)
            {

                ToolInfo toolInfo = new ToolInfo();
                JSONObject dataObj = dataArray.getJSONObject(i);

                toolInfo.setProcess(dataObj.getString("process"));


                infoArrayList.add(toolInfo);
            }

            for (int i = 0; i < infoArrayList.size();i++){
                processList.add(infoArrayList.get(i).getProcess());

            }


            ArrayAdapter<String> AspinnerArrayAdapter = new ArrayAdapter<String>(getContext(), simple_spinner_item , processList );
            AspinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(AspinnerArrayAdapter);
//            spinner.setSelection(0,true);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Process = spinner.getSelectedItem().toString();
//                    Toast.makeText(getContext(), Process, Toast.LENGTH_SHORT).show();

                    machineidList.clear();
                    GetValidCategory();


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (JSONException e){
            e.printStackTrace();

        }


    }


    private void GetValidCategory(){

        String json_process;
        json_process = "{\"process\":\"" + Process + "\"}";

        String data = "/api/tmsDev?toolcategory=" + json_process;

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        Call<String> call = retrofit.create(retro.GetValidCategory.class).getvalidcategory(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body());
//               Toast.makeText(getContext(), "IN RETROFIT", Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()){
                    if(response.body() !=null){
                        Log.i("OnSuccess",response.body());

//                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        String jsonresponse = response.body();
                        machineJson(jsonresponse);
                    }
                    else{
                        Log.i("onEmptyResponse", "Returned empty response");
                        Toast.makeText(getContext(),"fail",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void machineJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();

            String valid = obj.getString("count");

            if(valid.equals("1")) {
                JSONArray dataArray = obj.getJSONArray("info");
               spec_register.setEnabled(true);
                for (int i = 0; i < dataArray.length(); i++) {

                    ToolInfo toolInfo = new ToolInfo();
                    JSONObject dataObj = dataArray.getJSONObject(i);

                    toolInfo.setProcess(dataObj.getString("toolgroup"));

                    infoArrayList.add(toolInfo);
                }

                for (int i = 0; i < infoArrayList.size(); i++) {
                    machineidList.add(infoArrayList.get(i).getProcess());
                }

//                for (int i = 0; i < dataArray.length(); i++) {
//
//                    ToolInfo toolInfo = new ToolInfo();
//                    JSONObject dataObj = dataArray.getJSONObject(i);
//
//                    toolInfo.setProcess(dataObj.getString("toolgroup"));
//
//                    infoArrayList.add(toolInfo);
//                }
//
//                for (int i = 0; i < infoArrayList.size(); i++) {
//                    machineidList.add(infoArrayList.get(i).getProcess());
//                }


                ArrayAdapter<String> AspinnerArrayAdapter1 = new ArrayAdapter<String>(getContext(), simple_spinner_item, machineidList);
                AspinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(AspinnerArrayAdapter1);


            }


            else{

//                Toast.makeText(getContext(),"Machine Id not found",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage("Tool Category not found.Please add new category.");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
//             spinner2.setEnabled(false);
//             spinner3.setEnabled(false);
//                btn_newregister.setEnabled(false);
                spinner1.setAdapter(null);
                spec_register.setEnabled(false);

//                spinner1 = null;
//                spinner2.setAdapter(null);


            }
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Process2 = spinner2.getSelectedItem().toString();
//                    toolspecList.clear();
//                    GetValidSpec();
                    Toast.makeText(getContext(), "ToolSpecs", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });


        } catch (JSONException e){
            e.printStackTrace();

        }
    }




    public static new_spec newInstance() {
       new_spec fragment = new new_spec();
        return  fragment;

    }


    private  void checkFieldsForEmptyValues(){

        Button b = view.findViewById(R.id.register_spec);
        String s1 = tool_level.getText().toString();
//        boolean s2 = (spinner1.getSelectedItem().toString().trim() == "");


        if(s1.equals(""))
        {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setMessage("New Tool Specification Field is EMPTY!");
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()

            {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            b.setEnabled(false);
        }

        else
        {
            b.setEnabled(true);
        }
    }


    private void GetNewSpec(){

//        String Process = spinner.getSelectedItem().toString();
        String ToolGroupID = spinner1.getSelectedItem().toString();
        String mintoollevel = spinner2.getSelectedItem().toString();
        String toolspec = tool_level.getText().toString();

        String data ="/api/tmsDev?createtoolspec={\"toolgrp\":\"" + ToolGroupID + "\",\"mintoollevel\":\"" + mintoollevel + "\",\"toolspec\":\"" + toolspec + "\"}";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Call<String> call = retrofit.create(retro.GetNewSpec.class).getspec(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){
                    String count =response.body();

                    if(count.equals("{\"toolspec\":\"exist\"}")){
//                        Toast.makeText(getContext(), "Category Exist.Please insert new Tool Category", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setMessage("Tool Specification Exist.Please insert new Tool Specification.");
                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                    else{
                        Toast.makeText(getContext(), "Tool Specification Added Successfully", Toast.LENGTH_SHORT).show();
                    }

                }

                else{
                    Toast.makeText(getContext(), "Try Again", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }



}
