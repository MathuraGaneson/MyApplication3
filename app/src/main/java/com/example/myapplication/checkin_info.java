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
public class checkin_info extends Fragment {

    View view;
    private Button checkin_save ;

//    private EditText barcode;

//    TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void OnClickListener(Editable editable) {
//            Fragment checkininfo = new checkin_info();
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//            transaction.replace(R.id.checkin_info, ((checkin_info) checkininfo).newInstance());
//            transaction.commit();
//        }
//    };
    public static checkin_info newInstance() {
        // Required empty public constructor
        checkin_info Fragment =  new checkin_info();
        return Fragment;
    }




//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_checkin_info, container, false);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_checkin_info, container, false);

        View btn_save = view.findViewById(R.id.checkin_save);

//         = view.findViewById(R.id.editText);
//        barcode.setShowSoftInputOnFocus(false);
//        barcode.addTextChangedListener(textWatcher);
//        barcode.requestFocus();

//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment checkininfo = new checkin_info();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//                transaction.replace(R.id.checkin_save, ((checkininfo) checkin_save).newInstance());
//                transaction.commit();
//            }
//        });

        return view;
    }

}
