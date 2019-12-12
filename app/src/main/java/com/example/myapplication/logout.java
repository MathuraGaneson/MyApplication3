package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class logout extends Fragment {


    public logout() {
        // Required empty public constructor
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_logout, container, false);

            Button logout = view.findViewById(R.id.yes);
            Button logout_no = view.findViewById(R.id.no);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   logoutFragment();
                }
            });
            logout_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noFragment();
                }
            });
            return view;
        }


        private void logoutFragment(){
            Intent intent;
            intent = new Intent(getContext(), login2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);

        }

    private void noFragment(){
        Intent intent;
        intent = new Intent(getContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    private void finish() {
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_logout, container, false);
//    }

    public static logout newInstance() {
        logout fragment = new logout();
        return fragment;
    }
}
