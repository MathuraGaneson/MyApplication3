package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    Spinner spinner, spinner2, status;

    TextView employee, remark,machine,barcode;
    EditText machineID_keyin;
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
//        btn_reset = view.findViewById(R.id.checkout_reset);
        btn_save = view.findViewById(R.id.checkout_save);

        employee = view.findViewById(R.id.employee_name);
        machine = view.findViewById(R.id.machine_id2);
        remark = view.findViewById(R.id.remarksout2);
        barcode = view.findViewById(R.id.barcode_id2);
        machineID_keyin = view.findViewById(R.id.keyinmachine2);

        GetSharesPreferences();

//        spinner = view.findViewById(R.id.action_bar_spinner2);
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names2));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(myAdapter);

        validateSpinner();

//        spinner2 = view.findViewById(R.id.action_bar_spinner3);
//        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.machineid));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner2.setAdapter(myAdapter2);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();
            }
        });

//        btn_reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Reset Done",Toast.LENGTH_SHORT).show();
//
//                employee.setText("");
//                barcode.setText("");
//                remark.setText("");
//                machine.setText("");
//            }
//        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Fragment checkoutinfo = new checkout_info();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//                transaction.replace(R.id.tool_info, ((checkout_info) checkoutinfo).newInstance());
//                transaction.commit();
            }
        });
        return view;
    }

    private void validateSpinner() {

        status = view.findViewById(R.id.status_spinner);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.status));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(myAdapter3);

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str_status = status.getSelectedItem().toString();

                if (str_status.equals("Pm/faulty Checkout"))
                {
                    spinner.setEnabled(false);
                    spinner2.setEnabled(false);
                    machineID_keyin.setEnabled(true);
                }
                else if (str_status.equals("Checkout"))
                {
                    spinner.setEnabled(true);
                    spinner2.setEnabled(true);
                    machineID_keyin.setEnabled(false);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner = view.findViewById(R.id.action_bar_spinner2);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names2));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        spinner2 = view.findViewById(R.id.action_bar_spinner3);
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner2));
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(Adapter);

        spinner2.setEnabled(false);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String process = spinner.getSelectedItem().toString();

                if (process.equals("STB"))
                {
                    spinner2.setEnabled(false);
                    machineID_keyin.setEnabled(true);
                }
                else if (process.equals("ASA"))
                {
                    Toast.makeText(getContext(), process, Toast.LENGTH_SHORT).show();

                    ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.asa));
                    myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(myAdapter2);

                    machineID_keyin.setEnabled(false);
                    spinner2.setEnabled(true);
                }
                else if (process.equals("BALL ATTACH"))
                {
                    Toast.makeText(getContext(), process, Toast.LENGTH_SHORT).show();

                    ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.ball));
                    myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(myAdapter3);

                    machineID_keyin.setEnabled(false);
                    spinner2.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }




    private void GetSharesPreferences(){
        SharedPreferences prefs = getContext().getSharedPreferences("tms", Context.MODE_PRIVATE);
        employee.setText(prefs.getString("username","no data"));
    }


}
