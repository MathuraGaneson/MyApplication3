package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.HTTP;


/**
 * A simple {@link Fragment} subclass.
 */
public class test extends Fragment {
    private String apilink;
     View view;
     private Button btn_scan;
     private EditText barcode;

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


    public static test newInstance() {
        // Required empty public constructor
        test Fragment =  new test();
        return Fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_test, container, false);
//        apilink = getString(R.string.api);
//        btn_scan = view.findViewById(R.id.button_scan);

        barcode = view.findViewById(R.id.editText);
        barcode.setShowSoftInputOnFocus(false);
        barcode.addTextChangedListener(textWatcher);
        barcode.requestFocus();

//        btn_scan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment toolinfo = new tool_info();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//                transaction.replace(R.id.tool_info, ((tool_info) toolinfo).newInstance());
//                transaction.commit();
//            }
//        });
        return view;
    }



    private void GetValidbarcode(){
        String data = "/api/tms?barcodedata={\"barcodeid\":\"" + barcode.getText().toString() + "\"}";



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
//                      boolean valid = true;
//                      boolean valid2 = false;
                        if (valid.equals("\"true\"")){
                        Toast.makeText(getContext(), "Barcode Exist", Toast.LENGTH_SHORT).show();
                        Fragment toolinfo = new tool_info();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        transaction.replace(R.id.tool_info, ((tool_info) toolinfo).newInstance());
                        transaction.commit();
                    }else{
                        Toast.makeText(getContext(), "Barcode not exist!", Toast.LENGTH_SHORT).show();
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

           }
