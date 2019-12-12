package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.HTTP;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class test extends Fragment {
    private String apilink;
     View view;
     private Button btn_clear;
     private EditText barcode;
     ListView lv;
     ArrayList<ToolInfo> info;

    private static final String TAG = "MainActivity";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

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
            if(!TextUtils.isEmpty(barcode.getText().toString())){
                 GetValidbarcode();
            }


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
//        btn_clear = view.findViewById(R.id.clear);


//        btn_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                barcode.setText("");
//            }
//        });


        barcode.setShowSoftInputOnFocus(false);
        barcode.addTextChangedListener(textWatcher);
        barcode.requestFocus();

        hideKeyboard( getActivity());

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mEditor = mPreferences.edit();



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

    public static void hideKeyboard(Activity activity) {

        View view = activity.findViewById(android.R.id.content);

        if (view != null) {

            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }

    }




    private void checkbarcodesharepreferences(){
            mEditor = getContext().getSharedPreferences("barcodeshare", MODE_PRIVATE).edit();
            mEditor.putString("barcode", barcode.getText().toString());
            mEditor.commit();
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
//                    info = new ArrayList<>(Arrays.asList(valid.));
//                    lv = view.findViewById(R.id.listview);
//                    lv.setAdapter(new SearchToolAdapter(info, getActivity()));
//                      boolean valid = true;
//                      boolean valid2 = false;
                        if (valid.equals("\"true\"")){
                        Toast.makeText(getContext(), "Barcode Exist", Toast.LENGTH_SHORT).show();
                        //checkbarcodesharepreferences();
//                        barcode.setText("");
//                        Fragment toolinfo = new tool_info();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//                        transaction.replace(R.id.tool_info,tool_info.newInstance());
//                        transaction.commit();
//
//                        Toast.makeText(getContext(), barcode.getText().toString(), Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getContext(), "Barcode not exist!", Toast.LENGTH_SHORT).show();
//                        barcode.setText("");
                    }

                    checkbarcodesharepreferences();
                    barcode.setText("");
                    Fragment toolinfo = new tool_info();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    transaction.replace(R.id.tool_info,tool_info.newInstance());
                    transaction.commit();

//                    Toast.makeText(getContext(), barcode.getText().toString(), Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    barcode.setText("");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "NO CONNECTION", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void checkedittext(){
//
//        if(barcode.getText() != null){
//            barcode.setText("");
//        }else{
//
//        }
//
//           }

}
