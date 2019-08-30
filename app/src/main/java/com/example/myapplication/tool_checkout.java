package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class tool_checkout extends Fragment {


//    public static tool_checkout mewInstance() {
//      tool_checkout fragment = new tool_checkout();
//        return fragment;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tool_checkout, container, false);
    }


    public static tool_checkout newInstance() {
        tool_checkout fragment = new tool_checkout();
        return  fragment;
    }
}
