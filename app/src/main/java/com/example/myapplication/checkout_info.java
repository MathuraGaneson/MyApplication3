package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class checkout_info extends Fragment {

    View view;
    private Button checkout_save ;


//    public checkout_info() {
//        // Required empty public constructor
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        checkout_save = view.findViewById(R.id.checkout_save);
//        return inflater.inflate(R.layout.fragment_checkout_info, container, false);
//
//    }
//


    public static checkout_info newInstance() {
        // Required empty public constructor
        checkout_info Fragment =  new checkout_info();
        return Fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_checkout_info, container, false);

        View btn_save = view.findViewById(R.id.checkout_save);



        return view;
    }
}
