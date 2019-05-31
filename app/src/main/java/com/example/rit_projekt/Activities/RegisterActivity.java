package com.example.rit_projekt.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rit_projekt.Api.RetrofitClient;
import com.example.rit_projekt.Models.DefaultResponse;
import com.example.rit_projekt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etEmail,etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.et_ime);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_geslo);
        btnRegister = findViewById(R.id.btnRegist);

        btnRegister.setOnClickListener(this);
    }

    private void userSignUp(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String name = etName.getText().toString();

        if(email.isEmpty()){
            etEmail.setText("EMAIL REQUIRED");
            etEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setText("ENTER A VALID EMAIL");
            etEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etPassword.setText("ENTER PASSWORD");
            etPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            etPassword.setText("PASSWORD MUST BE ATLEAST 6 CHAR LONG");
            etPassword.requestFocus();
            return;
        }if(name.isEmpty()){
            etName.setText("ENTER NAME");
            etName.requestFocus();
            return;
        }

         Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email,password,name);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if(response.code() == 201){
                    DefaultResponse dr = response.body();

                    Toast.makeText(RegisterActivity.this,dr.getMessage(),Toast.LENGTH_LONG).show();
                }else if(response.code() == 422){
                    Toast.makeText(RegisterActivity.this,"User already exists",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnRegist:
                userSignUp();
                break;
        }
    }

}
