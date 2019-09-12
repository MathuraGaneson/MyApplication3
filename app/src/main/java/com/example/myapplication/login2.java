package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
//public class login2 extends Fragment {
//
//
//    public login2() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login2, container, false);
//    }
//
//}


public class login2 extends AppCompatActivity {
    private EditText login, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login2);
        login =  findViewById(R.id.login);
        password = findViewById(R.id.password);

        Button btn = findViewById(R.id.simpleButton);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
//                startActivity(new Intent(login2.this, MainActivity.class));
//                Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
//                finish();
                GetValid();
            }
        });
    }

    private void GetValid(){
        String data ="/api/tms?logindata={\"username\":\"" + login.getText().toString() + "\",\"password\":\"" + password.getText().toString() + "\"}";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(retro.GetValidUser.class).getvalidation(data);
       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
               if(response.isSuccessful()){
                   String valid = response.body();
                   if (valid.equals(true)) {
                       startActivity(new Intent(login2.this, MainActivity.class));
                       finish();
                   }else{
                       Toast.makeText(getApplicationContext(), "Invalid Username and Password!", Toast.LENGTH_SHORT).show();
                       login.setText("");
                       password.setText("");
                   }

               }else{

               }
           }

           @Override
           public void onFailure(Call<String> call, Throwable t) {
               Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
           }
       });

    }


}