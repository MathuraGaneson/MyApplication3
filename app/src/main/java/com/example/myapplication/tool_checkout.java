package com.example.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.R.layout.simple_spinner_item;
import static androidx.core.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class tool_checkout extends Fragment {

    View view;

    Button btn_checkout;
    Button btn_reset;
    Button btn_save;
    Spinner spinner, spinner2, status;

    TextView employee, remark,machine,barcode;
    EditText machineID_keyin;
    ToolInfo toolInfoList;

    ArrayList<ToolInfo> infoArrayList;

    ArrayList<String> processList = new ArrayList<>();
    ArrayList<String> machineidList = new ArrayList<>();

    private String mParam2;
    private String Process;

    private checkin.OnFragmentInteractionListener mListener;

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
//    public static tool_checkout mewInstance() {
//      tool_checkout fragment = new tool_checkout();
//        return fragment;
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tool_checkout, container, false);
//    }


    public static tool_checkout newInstance() {
        tool_checkout fragment = new tool_checkout();
        return  fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_tool_checkout, container, false);

        btn_checkout = view.findViewById(R.id.checkout_save);
//        btn_reset = view.findViewById(R.id.checkout_reset);
        btn_save = view.findViewById(R.id.checkout_save);

        employee = view.findViewById(R.id.employee_name);
        machine = view.findViewById(R.id.machine_id2);
        remark = view.findViewById(R.id.remarksout2);
        barcode = view.findViewById(R.id.barcode_checkout);
        machineID_keyin = view.findViewById(R.id.keyinmachine2);
        spinner = view.findViewById(R.id.action_bar_spinner2);
        spinner2 = view.findViewById(R.id.action_bar_spinner3);




        barcode.setShowSoftInputOnFocus(false);
        barcode.addTextChangedListener(textWatcher);
        barcode.setText("");
        barcode.requestFocus();
        GetValidProcess();


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



        GetSharesPreferences();



//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names2));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(myAdapter);

        validateSpinner();

//
//        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.machineid));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner2.setAdapter(myAdapter2);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();

               GetInsertdata();

//                String BarcodeId = barcode.getText().toString();
//                String EmployeeName = employee.getText().toString();
//                String MachineId = null;
//                String Remarks = remark.getText().toString();
//
//
//
//                if (spinner2.isEnabled() && !(machineID_keyin.isEnabled())) {
//
//                       MachineId = spinner2.getSelectedItem().toString();
//                }
//                else if(!spinner2.isEnabled() && machineID_keyin.isEnabled()){
//                       MachineId = machineID_keyin.getText().toString();
//                }

//                  String insertdata ="/api/TMS?CreateCheckoutData= {\"BarCodeID\":\"" + BarcodeId + "\",\"CardID\":\"" + EmployeeName + "\", \"MachineID\":\"" + MachineId + "\", \"RemarksOut\":\"" + Remarks + "\"}";
//                String insertdata = "{\"BarCodeID\":\"" + BarcodeId + "\",\"CardID\":\"" + EmployeeName + "\", \"MachineID\":\"" + MachineId + "\", \"RemarksOut\":\"" + Remarks + "\"}";
//                Toast.makeText(getContext(), insertdata, Toast.LENGTH_SHORT).show();
//                /remove after api uploaded/
            }
        });


//        btn_reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Reset Done",Toast.LENGTH_SHORT).show();
//
//                employee.setText("");
//                barcode.setText("");
//                remark.setText("");
//                machine.setText("");
//            }
//        });

//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Fragment checkoutinfo = new checkout_info();
////                FragmentTransaction transaction = getFragmentManager().beginTransaction();
////                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
////                transaction.replace(R.id.tool_info, ((checkout_info) checkoutinfo).newInstance());
////                transaction.commit();
//            }
//        });

//        barcode.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View view, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(barcode.getWindowToken(), 0);
//                    barcode.setFocusable(false);
//                    barcode.setFocusableInTouchMode(true);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
        return view;
    }



    public static void hideKeyboard(Activity activity) {

        View view = activity.findViewById(android.R.id.content);

        if (view != null) {

            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }

    }

    private void GetInsertdata(){

        String BarcodeId = barcode.getText().toString();
        String EmployeeName = employee.getText().toString();
        String MachineId = null;
        String Remarks = remark.getText().toString();


        if (spinner2.isEnabled() && !(machineID_keyin.isEnabled())) {

            MachineId = spinner2.getSelectedItem().toString();

        } else if (!spinner2.isEnabled() && machineID_keyin.isEnabled()) {
            MachineId = machineID_keyin.getText().toString();
        }

        final String data = "/api/TMSDev?CreateCheckoutData= {\"BarCodeID\":\"" + BarcodeId + "\",\"CardID\":\"" + EmployeeName + "\", \"MachineID\":\"" + MachineId + "\", \"RemarksOut\":\"" + Remarks + "\"}";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(retro.GetInsertdata.class).getinsertdata(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getContext(),data, Toast.LENGTH_SHORT).show();
//                 Toast.makeText(getContext(), "Checkout Success", Toast.LENGTH_SHORT).show();
                barcode.setText("");
                remark.setText("");
                machineID_keyin.setText("");
                }
                else{
                    Toast.makeText(getContext(), "Try Again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validateSpinner() {

        status = view.findViewById(R.id.status_spinner);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.status));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(myAdapter3);

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str_status = status.getSelectedItem().toString();

                if (str_status.equals("Pm/faulty Checkout"))
                {
                    spinner.setEnabled(false);
                    spinner2.setEnabled(false);
                    machineID_keyin.setEnabled(true);
                }
                else if (str_status.equals("Checkout"))
                {
                    spinner.setEnabled(true);
                    spinner2.setEnabled(true);
                    machineID_keyin.setEnabled(false);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//                String process = spinner.getSelectedItem().toString();
//
//                if (process.equals("STB"))
//                {
//                    spinner2.setEnabled(false);
//                    machineID_keyin.setEnabled(true);
//                }
//                else if (process.equals("ASA"))
//                {
//                    Toast.makeText(getContext(), process, Toast.LENGTH_SHORT).show();
//
//                    ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(getContext(),
//                            android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.asa));
//                    myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinner2.setAdapter(myAdapter2);
//
//                    machineID_keyin.setEnabled(false);
//                    spinner2.setEnabled(true);
//                }
//                else if (process.equals("BALL ATTACH"))
//                {
//                    Toast.makeText(getContext(), process, Toast.LENGTH_SHORT).show();
//
//                    ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(getContext(),
//                            android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.ball));
//                    myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinner2.setAdapter(myAdapter3);
//
//                    machineID_keyin.setEnabled(false);
//                    spinner2.setEnabled(true);
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) { }
//        });

    }


   private void GetValidProcess(){
         String data = "/api/tmsDev?outupdatedata=ok";

       Retrofit retrofit = new Retrofit
               .Builder()
               .baseUrl("http://pngjvfa01")
               .addConverterFactory(ScalarsConverterFactory.create())
               .build();

//       String test = "http://pngjvfa01" + data;


       Call<String> call = retrofit.create(retro.GetValidProcess.class).getvalidprocess(data);
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
            JSONArray dataArray = obj.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++)
            {

                ToolInfo toolInfo = new ToolInfo();
                JSONObject dataObj = dataArray.getJSONObject(i);

                toolInfo.setProcess(dataObj.getString("Process"));


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
                    GetValidMachineId();


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (JSONException e){
            e.printStackTrace();

        }


    }

    private void GetValidMachineId(){

        String json_process;
        json_process = "{\"process\":\"" + Process + "\"}";

        String data = "/api/tmsDev?machineid=" + json_process;

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        Call<String> call = retrofit.create(retro.GetValidProcess.class).getvalidprocess(data);
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

            if(valid.equals("True")){

//                Toast.makeText(getContext(), "true", Toast.LENGTH_SHORT).show();
                JSONArray dataArray = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++)
                {

                    ToolInfo toolInfo = new ToolInfo();
                    JSONObject dataObj = dataArray.getJSONObject(i);

                    toolInfo.setProcess(dataObj.getString("MachineID"));

                    infoArrayList.add(toolInfo);
                }

                for (int i = 0; i < infoArrayList.size();i++){
                    machineidList.add(infoArrayList.get(i).getProcess());
                }

                for (int i = 0; i < dataArray.length(); i++)
                {

                    ToolInfo toolInfo = new ToolInfo();
                    JSONObject dataObj = dataArray.getJSONObject(i);

                    toolInfo.setProcess(dataObj.getString("MachineID"));


                    infoArrayList.add(toolInfo);
                }

                for (int i = 0; i < infoArrayList.size();i++){
                    machineidList.add(infoArrayList.get(i).getProcess());
                }


                ArrayAdapter<String> AspinnerArrayAdapter1 = new ArrayAdapter<String>(getContext(), simple_spinner_item , machineidList );
                AspinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(AspinnerArrayAdapter1);

            }

            else{
//                Toast.makeText(getContext(),"Machine Id not found",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage("The machine ID not found.");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                spinner2.setAdapter(null);

            }



        } catch (JSONException e){
            e.printStackTrace();

        }
    }

    private void GetSharesPreferences(){
        SharedPreferences prefs = getContext().getSharedPreferences("tms", Context.MODE_PRIVATE);
        employee.setText(prefs.getString("username","no data"));
    }





}
