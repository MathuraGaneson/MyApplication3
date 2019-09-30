package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class tool_checkout extends Fragment {

    View view;

    Button btn_checkout;
    Button btn_reset;
    Button btn_save;

    TextView employee, remark,machine,barcode;
    private String mParam2;

    private checkin.OnFragmentInteractionListener mListener;
//    public static tool_checkout mewInstance() {
//      tool_checkout fragment = new tool_checkout();
//        return fragment;
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tool_checkout, container, false);
//    }


    public static tool_checkout newInstance() {
        tool_checkout fragment = new tool_checkout();
        return  fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_tool_checkout, container, false);

        btn_checkout = view.findViewById(R.id.checkout_save);
        btn_reset = view.findViewById(R.id.checkout_reset);
        btn_save = view.findViewById(R.id.checkout_save);

        employee = view.findViewById(R.id.employee_name);
        machine = view.findViewById(R.id.machine_id2);
        remark = view.findViewById(R.id.remarksout2);
        barcode = view.findViewById(R.id.barcode_id2);
        GetSharesPreferences();

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Reset Done",Toast.LENGTH_SHORT).show();

                employee.setText("");
                barcode.setText("");
                remark.setText("");
                machine.setText("");
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment checkoutinfo = new checkout_info();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                transaction.replace(R.id.tool_info, ((checkout_info) checkoutinfo).newInstance());
                transaction.commit();
            }
        });
        return view;
    }

    private void GetSharesPreferences(){
        SharedPreferences prefs = getContext().getSharedPreferences("tms", Context.MODE_PRIVATE);
        employee.setText(prefs.getString("username","no data"));
    }


}
