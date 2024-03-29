package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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


public class checkin extends Fragment {

    View view;

    Button btn_checkin;
    Button btn_reset;
    Button btn_save;
    Spinner spinner;

    TextView employee, remark,barcode;
    private String mParam2;

    ArrayList<ToolInfo> infoArrayList;

    ArrayList<String> statusList = new ArrayList<>();


    private OnFragmentInteractionListener mListener;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//            if (!barcode.getText().toString().equals(""))
//            {
//                Toast.makeText(getContext(), "Nt null", Toast.LENGTH_SHORT).show();
//                barcode.setText("");
//            }else{
//                Toast.makeText(getContext(), "Null", Toast.LENGTH_SHORT).show();
//            }
        }

        @Override
        public void afterTextChanged(Editable editable) {



//             Fragment toolinfo = new tool_info();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//                transaction.replace(R.id.tool_info, ((tool_info) toolinfo).newInstance());
//                transaction.commit();

//            if (!TextUtils.isEmpty(barcode.getText().toString()))
//            {
//                GetValidbarcode();
//            }

//            if (!barcode.getText().toString().equals(""))
//            {
//                Toast.makeText(getContext(), "Nt null", Toast.LENGTH_SHORT).show();
//                barcode.setText("");
//            }else{
//                Toast.makeText(getContext(), "Null", Toast.LENGTH_SHORT).show();
//            }

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//            if (!barcode.getText().toString().equals(""))
//            {
//                Toast.makeText(getContext(), "Nt null", Toast.LENGTH_SHORT).show();
//                barcode.setText("");
//            }else{
//                Toast.makeText(getContext(), "Null", Toast.LENGTH_SHORT).show();
//            }
        }
    };
    public static checkin newInstance() {
        checkin fragment = new checkin();
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_checkin, container, false);

//        btn_checkin = view.findViewById(R.id.checkin_save);
        btn_reset = view.findViewById(R.id.checkin_reset);

        employee = view.findViewById(R.id.employee_name);

        GetStatus();

        barcode = view.findViewById(R.id.barcode_checkin);
        barcode.setShowSoftInputOnFocus(false);
        barcode.addTextChangedListener(textWatcher);
        barcode.requestFocus();

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

//        barcode.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                 String checkinbarcode = barcode.getText().toString();
//                if (keyCode == KeyEvent.KEYCODE_ENTER && barcode.length() > 0) {
//                    if (barcode.equals(null)){
//                        barcode.setText(checkinbarcode);
//                    }else if (!barcode.equals(null))
//                    {
//                        barcode.setText("");
//                        barcode.setText(checkinbarcode);
//                    }
////                    (checkinbarcode);
//                    return true;
//                }
//
//                return false;
//            }
//        });

        remark = view.findViewById(R.id.remarks2);
//        status = view.findViewById(R.id.status2);
        hideKeyboard( getActivity());
        GetSharesPreferences();

        spinner = view.findViewById(R.id.action_bar_spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        btn_save = view.findViewById(R.id.checkin_save);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Reset Done",Toast.LENGTH_SHORT).show();

                barcode.setText("");
                remark.setText("");
//                status.setText("");
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Getcheckin();
//                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();
                GetValidbarcode();
//                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();

//                String BarCodeID = barcode.getText().toString();
//                String RemarksIn = remark.getText().toString();
//                String Status = spinner.getSelectedItem().toString();
//
//                final String updatedata = "/api/tms?toolinupdate=(\"Barcodeid\":\"" + BarCodeID + "\",\"Remarks\":\"" + RemarksIn + "\",\"Status\":\"" + Status + "\")";
////                Toast.makeText(getContext(),updatedata,Toast.LENGTH_SHORT).show();
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://pngjvfa01/")
//                        .addConverterFactory(ScalarsConverterFactory.create())
//                        .build();
//
//                Call<String> call = retrofit.create(retro.GetValidbarcode.class).getvalidationbarcode(updatedata);
            }
        });
        return  view;

    }

    public static void hideKeyboard(Activity activity) {

        View view = activity.findViewById(android.R.id.content);

        if (view != null) {

            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }

    }
 private void GetSharesPreferences(){
     SharedPreferences prefs = getContext().getSharedPreferences("tms", Context.MODE_PRIVATE);
     employee.setText(prefs.getString("username","no data"));
 }

    private void GetStatus(){
        String data = "/api/tmsDev?statuslist=ok";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//       String test = "http://pngjvfa01" + data;


        Call<String> call = retrofit.create(retro.GetStatus1.class).getstatus1(data);
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
            spinner.setAdapter(AspinnerArrayAdapter5);
//            spinner.setSelection(0,true);




        } catch (JSONException e){
            e.printStackTrace();

        }


    }

private  void Getcheckin(){
                String BarCodeID = barcode.getText().toString();
                String RemarksIn = remark.getText().toString();
                String Status = spinner.getSelectedItem().toString();
                String ReturnBy = employee.getText().toString();

                final String updatedata = "/api/tmsDev?toolin={\"BarCodeID\":\"" + BarCodeID + "\",\"RemarksIn\":\"" + RemarksIn + "\",\"Status\":\"" + Status + "\",\"ReturnBy\":\"" + ReturnBy + "\"}";
//                Toast.makeText(getContext(),updatedata,Toast.LENGTH_SHORT).show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://pngjvfa01")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

                Call<String> call = retrofit.create(retro.Getcheckin.class).getvalidcheckin(updatedata);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            String count =response.body();

                            if(count.equals("{\"Update\":\"Already Update\"}")){
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                                alertDialogBuilder.setMessage("Already Updated");
                                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();

                            }
                            else{
                                Toast.makeText(getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();


                            }


                        }

                        else{
                            Toast.makeText(getContext(), "Try Again", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
//                        Toast.makeText(getContext(),"Fail", Toast.LENGTH_SHORT).show();
                    }
                });
}

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void GetValidbarcode(){
        String data = "/api/tmsDev?barcodedata={\"barcodeid\":\"" + barcode.getText().toString() + "\"}";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(retro.GetValidbarcode.class).getvalidationbarcode(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String valid = response.body();
                    if (valid.equals("\"true\"")){
//                        Toast.makeText(getContext(), "Barcode Exist", Toast.LENGTH_SHORT).show();
                      Getcheckin();

                    }else{
                        Toast.makeText(getContext(), "Barcode not exist!", Toast.LENGTH_SHORT).show();
//
                    }

                }
                else{
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "NO CONNECTION", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
