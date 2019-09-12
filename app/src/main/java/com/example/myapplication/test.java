package com.example.myapplication;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class test extends Fragment {

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
             Fragment toolinfo = new tool_info();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                transaction.replace(R.id.tool_info, ((tool_info) toolinfo).newInstance());
                transaction.commit();
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

           }
