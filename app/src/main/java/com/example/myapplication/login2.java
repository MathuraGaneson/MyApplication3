package com.example.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

    private static final String TAG = "MainActivity";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


    private EditText login, password,employee;
    private android.util.Log Log;
    private TextView version;

    public static login2 newInstance() {
        login2 Fragment =  new login2();
        return Fragment;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login2);

        login =  findViewById(R.id.login);
        password = findViewById(R.id.password);
        employee = findViewById(R.id.employee_name);
        version = findViewById(R.id.version_name);


        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        setVersionName();


//        checkSharedPreferences();
        Button btn = findViewById(R.id.simpleButton);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
//                startActivity(new Intent(login2.this, MainActivity.class));
//                Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
//                finish();

//                String name = employee.getText().toString();
//                mEditor.putString(getString(R.string.name), name);
//                mEditor.commit();

                GetValid();
            }
        });
    }
    private void checkSharedPreferences(){
         mEditor = getApplicationContext().getSharedPreferences("tms", MODE_PRIVATE).edit();
         mEditor.putString("username", login.getText().toString());
         mEditor.commit();
//        String name = mPreferences.getString(getString(R.string.name),"");

//        employee.setText(name);

    }


    private void GetValid(){
       String data ="/api/tmsDev?logindata={\"username\":\"" + login.getText().toString() + "\",\"password\":\"" + password.getText().toString() + "\"}";



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
                   if (valid.equals("true")) {
                       checkSharedPreferences();
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
               Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_SHORT).show();
           }
       });

    }

    private void setVersionName(){
        version.setText("Version: " + BuildConfig.VERSION_NAME);
    }


}