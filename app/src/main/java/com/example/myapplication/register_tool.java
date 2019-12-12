package com.example.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.R.layout.simple_spinner_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class register_tool extends Fragment {

    private static final String TAG = "MainActivity";

    View view;
    private EditText text, barcode,decription;
  private  ImageButton image;
  private DatePickerDialog.OnDateSetListener date;
  private Button btn_newregister;
  private Spinner spinner, spinner2,spinner3,spinner4,spinnerpm,spinnerstatus,spinnerrack,spinnerrow,spinnersection;



    ArrayList<ToolInfo> infoArrayList;

    ArrayList<String> processList = new ArrayList<>();
    ArrayList<String> machineidList = new ArrayList<>();
    ArrayList<String> toolspecList = new ArrayList<>();
    ArrayList<String> vendorList = new ArrayList<>();
    ArrayList<String> pmList = new ArrayList<>();
    ArrayList<String> statusList = new ArrayList<>();
    ArrayList<String> rackList = new ArrayList<>();
    ArrayList<String> rowList = new ArrayList<>();
    ArrayList<String> sectionList = new ArrayList<>();

    private String mParam2;
    private String Process, Process2, Processrack,Processrow;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public register_tool() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register_tool, container, false);


        image = view.findViewById(R.id.img);
        text = view.findViewById(R.id.date);
        btn_newregister = view.findViewById(R.id.register_save);
        barcode = view.findViewById(R.id.new_barcode2);
        decription = view.findViewById(R.id.new_desc2);
        spinner = view.findViewById(R.id.spinner_process);
        spinner2 = view.findViewById(R.id.spinner_category);
        spinner3 = view.findViewById(R.id.spinner_spec);
        spinner4 = view.findViewById(R.id.spinner_manufacturer);
        spinnerpm = view.findViewById(R.id.spinner_pmcycle);
        spinnerstatus = view.findViewById(R.id.spinner_status);
        spinnerrack = view.findViewById(R.id.spinner_rack);
        spinnerrow = view.findViewById(R.id.spinner_row);
        spinnersection = view.findViewById(R.id.spinner_section);




//        btn_newregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
//            }
//        });
////                newregister();

        btn_newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          GetNewRegister();
//           if(spinner.equals("")&& spinner2.equals("")){
//               Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
//           }
////                Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
//            else{
//                GetNewRegister();
//            }

            }

            });

//        if(spinner2.getSelectedItem() == "" || spinner3.getSelectedItem() == ""){
//            btn_newregister.setEnabled(false);
//        }
//        else{
//            btn_newregister.setEnabled(true);
//        }


        barcode.setShowSoftInputOnFocus(false);
        barcode.addTextChangedListener(textWatcher);
        barcode.setText("");
        barcode.requestFocus();

        GetValidRegisterProcess();

        GetVendor();

        GetPm();

        GetStatus();

        GetRack();

//        GetSection();






        barcode.setInputType(InputType.TYPE_CLASS_TEXT);
        barcode.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(barcode, InputMethodManager.SHOW_FORCED);


        barcode.setClickable(false);

        barcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(getActivity());
            }
        });

        barcode.requestFocus();




        image.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog_MinWidth,date,year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {




            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG,"onDateSet: mm/dd/yyy:" + month + "/" + day + "/" + year);

                String date1 = month + "/" + day + "/" +year;
                text.setText(date1);

            }
        };

 return view;
    }

    public static void hideKeyboard(Activity activity) {

        View view = activity.findViewById(android.R.id.content);

        if (view != null) {

            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }

    }

    public static register_tool newInstance() {
        register_tool fragment = new register_tool();
        return  fragment;
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

                      Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
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
                btn_newregister.setEnabled(true);
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
                spinner2.setAdapter(AspinnerArrayAdapter1);


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
                spinner3.setAdapter(null);
                spinner2.setAdapter(null);
                btn_newregister.setEnabled(false);

            }
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Process2 = spinner2.getSelectedItem().toString();
                    toolspecList.clear();
                    GetValidSpec();
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

    private void GetValidSpec(){

        String json_process;
        json_process = "{\"toolgrp\":\"" + Process2 + "\"}";

        String data = "/api/tmsDev?toolspec=" + json_process;

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        Call<String> call = retrofit.create(retro.GetValidSpec.class).getvalidspec(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body());
//               Toast.makeText(getContext(), "IN ToolSpec Retro", Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()){
                    if(response.body() !=null){
                        Log.i("OnSuccess",response.body());

                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        String jsonresponse = response.body();
                        specJson(jsonresponse);
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

    private void specJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

//            Toast.makeText(getContext(), "populate toolspec", Toast.LENGTH_SHORT).show();
            infoArrayList = new ArrayList<>();

            String valid = obj.getString("count");

            if(valid.equals("1")) {


                JSONArray dataArray = obj.getJSONArray("info");
                btn_newregister.setEnabled(true);
                for (int i = 0; i < dataArray.length(); i++) {

                    ToolInfo toolInfo = new ToolInfo();
                    JSONObject dataObj = dataArray.getJSONObject(i);

                    toolInfo.setProcess(dataObj.getString("toolspec"));

                    infoArrayList.add(toolInfo);

//                    Toast.makeText(getContext(), "list exist", Toast.LENGTH_SHORT).show();
                }

                for (int i = 0; i < infoArrayList.size(); i++) {
                    toolspecList.add(infoArrayList.get(i).getProcess());
                }

//                for (int i = 0; i < dataArray.length(); i++) {
//
//                    ToolInfo toolInfo = new ToolInfo();
//                    JSONObject dataObj = dataArray.getJSONObject(i);
//
//                    toolInfo.setProcess(dataObj.getString("toolspec"));
//
//
//                    infoArrayList.add(toolInfo);
//                }
//
//                for (int i = 0; i < infoArrayList.size(); i++) {
//                    machineidList.add(infoArrayList.get(i).getProcess());
//                }


                ArrayAdapter<String> AspinnerArrayAdapter2 = new ArrayAdapter<String>(getContext(), simple_spinner_item, toolspecList);
                AspinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner3.setAdapter(AspinnerArrayAdapter2);

            }

            else{
//                Toast.makeText(getContext(),"Machine Id not found",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage("The Tool Specification not found.Please add tool specification.");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
//                spinner3.setEnabled(false);
                spinner3.setAdapter(null);
                btn_newregister.setEnabled(false);

            }

//            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Process3 = spinner3.getSelectedItem().toString();
////                    Toast.makeText(getContext(), Process, Toast.LENGTH_SHORT).show();
//
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//
//            });


        } catch (JSONException e){
            e.printStackTrace();

        }
    }

    private void GetVendor(){
        String data = "/api/tmsDev?vendorlist=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetVendor.class).getvendor(data);
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
                        vendorJson(jsonresponse);
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


    private void vendorJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("info");

            for (int i = 0; i < dataArray.length(); i++)
            {

                ToolInfo toolInfo = new ToolInfo();
                JSONObject dataObj = dataArray.getJSONObject(i);

                toolInfo.setProcess(dataObj.getString("vendor"));


                infoArrayList.add(toolInfo);
            }

            for (int i = 0; i < infoArrayList.size();i++){
                vendorList.add(infoArrayList.get(i).getProcess());

            }


            ArrayAdapter<String> AspinnerArrayAdapter3 = new ArrayAdapter<String>(getContext(), simple_spinner_item , vendorList );
            AspinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner4.setAdapter(AspinnerArrayAdapter3);
//            spinner.setSelection(0,true);




        } catch (JSONException e){
            e.printStackTrace();

        }


    }

    private void GetPm(){
        String data = "/api/tmsDev?pmlist=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetPm.class).getpm(data);
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
                        pmJson(jsonresponse);
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

    private void pmJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("info");

            for (int i = 0; i < dataArray.length(); i++)
            {

                ToolInfo toolInfo = new ToolInfo();
                JSONObject dataObj = dataArray.getJSONObject(i);

                toolInfo.setProcess(dataObj.getString("pm"));


                infoArrayList.add(toolInfo);
            }

            for (int i = 0; i < infoArrayList.size();i++){
                pmList.add(infoArrayList.get(i).getProcess());

            }


            ArrayAdapter<String> AspinnerArrayAdapter4 = new ArrayAdapter<String>(getContext(), simple_spinner_item , pmList );
            AspinnerArrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerpm.setAdapter(AspinnerArrayAdapter4);
//            spinner.setSelection(0,true);




        } catch (JSONException e){
            e.printStackTrace();

        }


    }

    private void GetStatus(){
        String data = "/api/tmsDev?statuslist=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetStatus.class).getstatus(data);
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
                        statusJson(jsonresponse);
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


    private void statusJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("info");

            for (int i = 0; i < dataArray.length(); i++)
            {

                ToolInfo toolInfo = new ToolInfo();
                JSONObject dataObj = dataArray.getJSONObject(i);

                toolInfo.setProcess(dataObj.getString("status"));


                infoArrayList.add(toolInfo);
            }

            for (int i = 0; i < infoArrayList.size();i++){
                statusList.add(infoArrayList.get(i).getProcess());

            }


            ArrayAdapter<String> AspinnerArrayAdapter5 = new ArrayAdapter<String>(getContext(), simple_spinner_item , statusList );
            AspinnerArrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerstatus.setAdapter(AspinnerArrayAdapter5);
//            spinner.setSelection(0,true);




        } catch (JSONException e){
            e.printStackTrace();

        }


    }

    private void GetRack(){
        String data = "/api/tmsDev?racklist=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetRack.class).getrack(data);
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
                        rackJson(jsonresponse);
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

    private void rackJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("info");

            for (int i = 0; i < dataArray.length(); i++)
            {

                ToolInfo toolInfo = new ToolInfo();
                JSONObject dataObj = dataArray.getJSONObject(i);

                toolInfo.setProcess(dataObj.getString("rack"));


                infoArrayList.add(toolInfo);
            }

            for (int i = 0; i < infoArrayList.size();i++){
                rackList.add(infoArrayList.get(i).getProcess());

            }


            ArrayAdapter<String> AspinnerArrayAdapter6 = new ArrayAdapter<String>(getContext(), simple_spinner_item , rackList );
            AspinnerArrayAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerrack.setAdapter(AspinnerArrayAdapter6);
//            spinner.setSelection(0,true);

            spinnerrack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Processrack = spinnerrack.getSelectedItem().toString();
//                    Toast.makeText(getContext(), Process, Toast.LENGTH_SHORT).show();
                    rowList.clear();
                    GetRow();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (JSONException e){
            e.printStackTrace();

        }


    }

    private void GetRow(){

        String json_process;
        json_process = "{\"rack\":\"" + Processrack + "\"}";

        String data = "/api/tmsDev?rowlist=" + json_process;

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        Call<String> call = retrofit.create(retro.GetRow.class).getrow(data);
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
                        rowJson(jsonresponse);
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

    private void rowJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();

            String valid = obj.getString("count");

            if(valid.equals("1")){

//                Toast.makeText(getContext(), "true", Toast.LENGTH_SHORT).show();
                JSONArray dataArray = obj.getJSONArray("info");

                for (int i = 0; i < dataArray.length(); i++)
                {

                    ToolInfo toolInfo = new ToolInfo();
                    JSONObject dataObj = dataArray.getJSONObject(i);

                    toolInfo.setProcess(dataObj.getString("row"));

                    infoArrayList.add(toolInfo);
                }

                for (int i = 0; i < infoArrayList.size();i++){
                    rowList.add(infoArrayList.get(i).getProcess());
                }

//                for (int i = 0; i < dataArray.length(); i++)
//                {
//
//                    ToolInfo toolInfo = new ToolInfo();
//                    JSONObject dataObj = dataArray.getJSONObject(i);
//
//                    toolInfo.setProcess(dataObj.getString("row"));
//
//
//                    infoArrayList.add(toolInfo);
//                }
//
//                for (int i = 0; i < infoArrayList.size();i++){
//                    rowList.add(infoArrayList.get(i).getProcess());
//                }


                ArrayAdapter<String> AspinnerArrayAdapter7 = new ArrayAdapter<String>(getContext(), simple_spinner_item , rowList );
                AspinnerArrayAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerrow.setAdapter(AspinnerArrayAdapter7);


                spinnerrow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Processrow = spinnerrow.getSelectedItem().toString();
                        sectionList.clear();
                    GetSection();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            else{
//                Toast.makeText(getContext(),"Machine Id not found",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage("Rack not found.");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
//                spinnerrow.setAdapter(null);

            }



        } catch (JSONException e){
            e.printStackTrace();

        }
    }

    private void GetSection(){

        String json_process, json_section, section;
        section = "{\"Rack\" :\"" + Processrack + "\",\"Row\" : " + "\"" + Processrow + "\" }"
        ;

//        json_section = "{\"Row\":\"" + Processrow + "\"}";
//
//        section = json_process + json_section;

        String data = "/api/tmsDev?sectionlist=" + section;

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        Call<String> call = retrofit.create(retro.GetSection.class).getsection(data);
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
                        sectionJson(jsonresponse);
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


    private void sectionJson(String jsonresponse){

        try {

            JSONObject obj = new JSONObject(jsonresponse);

            infoArrayList = new ArrayList<>();

            String valid = obj.getString("count");

            if(valid.equals("1")){

//                Toast.makeText(getContext(), "true", Toast.LENGTH_SHORT).show();
                JSONArray dataArray = obj.getJSONArray("info");

                for (int i = 0; i < dataArray.length(); i++)
                {

                    ToolInfo toolInfo = new ToolInfo();
                    JSONObject dataObj = dataArray.getJSONObject(i);

                    toolInfo.setProcess(dataObj.getString("section"));

                    infoArrayList.add(toolInfo);
                }

                for (int i = 0; i < infoArrayList.size();i++){
                    sectionList.add(infoArrayList.get(i).getProcess());
                }

//                for (int i = 0; i < dataArray.length(); i++)
//                {
//
//                    ToolInfo toolInfo = new ToolInfo();
//                    JSONObject dataObj = dataArray.getJSONObject(i);
//
//                    toolInfo.setProcess(dataObj.getString("row"));
//
//
//                    infoArrayList.add(toolInfo);
//                }
//
//                for (int i = 0; i < infoArrayList.size();i++){
//                    rowList.add(infoArrayList.get(i).getProcess());
//                }


                ArrayAdapter<String> AspinnerArrayAdapter8 = new ArrayAdapter<String>(getContext(), simple_spinner_item , sectionList );
                AspinnerArrayAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnersection.setAdapter(AspinnerArrayAdapter8);


//                spinnersection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        Processrow = spinnerrow.getSelectedItem().toString();
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });

            }

            else{
//              Toast.makeText(getContext(),"Machine Id not found",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage("Section not found.");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                spinnersection.setAdapter(null);

            }



        } catch (JSONException e){
            e.printStackTrace();

        }
    }

    private void GetNewRegister(){


//        String Process = spinner.getSelectedItem().toString();
        String ToolGrp = spinner2.getSelectedItem().toString();
        String ToolSpec = spinner3.getSelectedItem().toString();
        String BarCodeID = barcode.getText().toString();
        String Name = decription.getText().toString();
        String Vendor = spinner4.getSelectedItem().toString();
        String PMID = spinnerpm.getSelectedItem().toString();
        String LPMDate = text.getText().toString();
        String Status = spinnerstatus.getSelectedItem().toString();
        String Rack = spinnerrack.getSelectedItem().toString();
        String Row = spinnerrow.getSelectedItem().toString();
        String Section = spinnersection.getSelectedItem().toString();



        String data ="/api/tmsDev?createtools={\"ToolGrp\":\"" + ToolGrp + "\",\"ToolSpec\":\"" + ToolSpec + "\",\"BarcodeID\":\"" + BarCodeID + "\",\"Name\":\"" + Name + "\", \"Vendor\":\"" + Vendor + "\", \"PMID\":\"" + PMID + "\",\"LPMDate\":\"" + LPMDate + "\",\"Status\":\"" + Status + "\",\"Rack\":\"" + Rack + "\",\"Row\":\"" + Row + "\",\"Section\":\"" + Section + "\"}";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Call<String> call = retrofit.create(retro.GetNewRegister.class).getregister(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                    barcode.setText("");
                    decription.setText("");
                    text.setText("");
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
