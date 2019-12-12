package com.example.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
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
public class new_group extends Fragment {

    View view;
private Spinner spinner1,spinner2,spinner3;
private Button category_register;
private EditText new_category;


ArrayList<ToolInfo> infoArrayList;

ArrayList<String> processList = new ArrayList<>();
ArrayList<String> piclist = new ArrayList<>();
ArrayList<String> resplist = new ArrayList<>();

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
    public new_group() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_group, container, false);

        spinner1 = view.findViewById(R.id.category_process);
        spinner2 = view.findViewById(R.id.category_pic);
        spinner3 = view.findViewById(R.id.category_resp);
        category_register = view.findViewById(R.id.register_category);
        new_category = view.findViewById(R.id.new_group2);

       GetPic();
       GetResp();
        new_category.addTextChangedListener(textWatcher);
        checkFieldsForEmptyValues();

        category_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                GetNewCategory();

            }

        });

//        if(new_category.getText().toString().trim().length()>0){
//            category_register.setEnabled(false);
//        }

        GetValidNewProcess();
        return view;
    }

    public static new_group newInstance() {
        new_group fragment = new new_group();
        return  fragment;
    }

    private void GetValidNewProcess(){
        String data = "/api/tmsDev?processList=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetNewProcess.class).getnewprocess(data);
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
            spinner1.setAdapter(AspinnerArrayAdapter);
//            spinner.setSelection(0,true);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Process = spinner.getSelectedItem().toString();
////                    Toast.makeText(getContext(), Process, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (JSONException e){
            e.printStackTrace();

        }


    }

    private void GetPic(){
        String data = "/api/tmsDev?piclist=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetPIC.class).getpic(data);
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
                        picJson(jsonresponse);
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

    private void picJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("info");

            for (int i = 0; i < dataArray.length(); i++)
            {

                ToolInfo toolInfo = new ToolInfo();
                JSONObject dataObj = dataArray.getJSONObject(i);

                toolInfo.setProcess(dataObj.getString("PIC"));


                infoArrayList.add(toolInfo);
            }

            for (int i = 0; i < infoArrayList.size();i++){
                piclist.add(infoArrayList.get(i).getProcess());

            }


            ArrayAdapter<String> AspinnerArrayAdapter1 = new ArrayAdapter<String>(getContext(), simple_spinner_item , piclist );
            AspinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(AspinnerArrayAdapter1);
//            spinner.setSelection(0,true);

            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Process = spinner.getSelectedItem().toString();
////                    Toast.makeText(getContext(), Process, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (JSONException e){
            e.printStackTrace();

        }


    }

    private void GetResp(){
        String data = "/api/tmsDev?resposibilitylist=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetResp.class).getresp(data);
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
                        respJson(jsonresponse);
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

    private void respJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("info");

            for (int i = 0; i < dataArray.length(); i++)
            {

                ToolInfo toolInfo = new ToolInfo();
                JSONObject dataObj = dataArray.getJSONObject(i);

                toolInfo.setProcess(dataObj.getString("resposibility"));


                infoArrayList.add(toolInfo);
            }

            for (int i = 0; i < infoArrayList.size();i++){
                resplist.add(infoArrayList.get(i).getProcess());

            }


            ArrayAdapter<String> AspinnerArrayAdapter3 = new ArrayAdapter<String>(getContext(), simple_spinner_item , resplist );
            AspinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(AspinnerArrayAdapter3);
//            spinner.setSelection(0,true);

            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Process = spinner.getSelectedItem().toString();
////                    Toast.makeText(getContext(), Process, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (JSONException e){
            e.printStackTrace();

        }


    }


    private void GetNewCategory(){

        String Process = spinner1.getSelectedItem().toString();
        String PIC = spinner2.getSelectedItem().toString();
        String Responsibility = spinner3.getSelectedItem().toString();
        String Description = new_category.getText().toString();

        String data ="/api/tmsDev?createtoolgroup={\"process\":\"" + Process + "\",\"pic\":\"" + PIC + "\",\"responsibility\":\"" + Responsibility + "\",\"toolgroup\":\"" + Description + "\"}";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Call<String> call = retrofit.create(retro.GetNewCategory.class).getcategory(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){
                    String count =response.body();

                    if(count.equals("{\"toolgroup\":\"exist\"}")){
//                        Toast.makeText(getContext(), "Category Exist.Please insert new Tool Category", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setMessage("Category Exist.Please insert new Tool Category.");
                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                    else{
                        Toast.makeText(getContext(), "Tool Category Added Successfully", Toast.LENGTH_SHORT).show();


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

    private  void checkFieldsForEmptyValues(){

        Button b = view.findViewById(R.id.register_category);
        String s1 = new_category.getText().toString();


        if(s1.equals(""))
        {
            b.setEnabled(false);

        }

        else
        {
            b.setEnabled(true);
        }
    }


}
