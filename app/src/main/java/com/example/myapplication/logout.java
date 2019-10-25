package com.example.myapplication;


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
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   logoutFragment();
                }
            });
            return view;
        }

        private void logoutFragment(){
                Fragment loginnew = new Fragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                transaction.replace(R.id.login,loginnew);
                transaction.addToBackStack(null);
                transaction.commit();

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
