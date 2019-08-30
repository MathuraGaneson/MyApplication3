package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */






    public class tool_info extends Fragment {
        View view;

        Button btn_save;

        public static tool_info newInstance() {
            // Required empty public constructor
            tool_info Fragment =  new tool_info();
            return Fragment;
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_tool_info, container, false);

        btn_save = view.findViewById(R.id.button_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
    }

}
