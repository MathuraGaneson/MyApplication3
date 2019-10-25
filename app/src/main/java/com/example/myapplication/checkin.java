package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class checkin extends Fragment {

    View view;

    Button btn_checkin;
    Button btn_reset;
    Button btn_save;
    Spinner spinner;

    TextView employee, remark,barcode;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
//             Fragment toolinfo = new tool_info();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//                transaction.replace(R.id.tool_info, ((tool_info) toolinfo).newInstance());
//                transaction.commit();
            GetValidbarcode();
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

        btn_checkin = view.findViewById(R.id.checkin_save);
        btn_reset = view.findViewById(R.id.checkin_reset);

        employee = view.findViewById(R.id.employee_name);

        barcode = view.findViewById(R.id.barcode_checkin);
        barcode.setShowSoftInputOnFocus(false);
        barcode.addTextChangedListener(textWatcher);
        barcode.requestFocus();

        remark = view.findViewById(R.id.remarks2);
//        status = view.findViewById(R.id.status2);
        GetSharesPreferences();

        spinner = view.findViewById(R.id.action_bar_spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        btn_save = view.findViewById(R.id.checkin_save);

        btn_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();
//
//                String Remarks = remark.getText().toString();
//                String Status = spinner.getSelectedItem().toString();
//
//                String updatedata = "(\"Remarks\":\"" + Remarks + "\",\"Status\":\"" + Status + "\")";
//                Toast.makeText(getContext(),updatedata,Toast.LENGTH_SHORT).show();

            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Reset Done",Toast.LENGTH_SHORT).show();

                employee.setText("");
                barcode.setText("");
                remark.setText("");
//                status.setText("");
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();

                String BarCodeID = barcode.getText().toString();
                String RemarksIn = remark.getText().toString();
                String Status = spinner.getSelectedItem().toString();

//                String updatedata = "(\"Barcodeid\":\"" + BarCodeID + "\",\"Remarks\":\"" + RemarksIn + "\",\"Status\":\"" + Status + "\")";
//                Toast.makeText(getContext(),updatedata,Toast.LENGTH_SHORT).show();

            }
        });
        return  view;
    }


 private void GetSharesPreferences(){
     SharedPreferences prefs = getContext().getSharedPreferences("tms", Context.MODE_PRIVATE);
     employee.setText(prefs.getString("username","no data"));
 }


    private void GetValidbarcode(){

        final String data = "/api/tms?barcodedata={\"barcodeid\":\"" + barcode.getText().toString() + "\"}";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(retro.GetValidbarcode.class).getvalidationbarcode(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String valid = response.body();
//                    info = new ArrayList<>(Arrays.asList(valid.));
//                    lv = view.findViewById(R.id.listview);
//                    lv.setAdapter(new SearchToolAdapter(info, getActivity()));
//                      boolean valid = true;
//                      boolean valid2 = false;

                    if (valid.equals("\"false\"")){
                        Toast.makeText(getContext(), "Tool Not Exist", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getContext(), "Tool exist!", Toast.LENGTH_SHORT).show();
                        barcode.setText("");


                    }

                }
                else{
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    barcode.setText("");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "TEST", Toast.LENGTH_SHORT).show();
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


}
