package com.example.myapplication;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class testing extends Fragment {
    ArrayList<ToolInfo>datamodels;
  ListView listView;
  private static SearchToolAdapter adapter;
 View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_testing, container, false);

//        listView = view.findViewById(R.id.listview);
        adapter=new SearchToolAdapter(getContext(),datamodels);

        listView.setAdapter(adapter);

        return view;

    }

    public static testing newInstance() {
        testing fragment = new testing();
        return  fragment;
    }
}
