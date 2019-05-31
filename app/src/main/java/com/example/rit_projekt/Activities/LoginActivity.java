package com.example.rit_projekt.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rit_projekt.Api.RetrofitClient;
import com.example.rit_projekt.Models.LoginResponse;
import com.example.rit_projekt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail,etPassword;
    TextView tvRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_mail);
        etPassword = findViewById(R.id.et_pass);
        btnLogin = findViewById(R.id.btnPrijava);
        tvRegister = findViewById(R.id.tvRegis);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

    }

    private void userLogin(){

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

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
        }
        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(!loginResponse.isError()){

                    Toast.makeText(LoginActivity.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(LoginActivity.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
        startActivity(new Intent(this,MainActivity.class));
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnPrijava:
                userLogin();

                break;
            case R.id.tvRegis:
                Intent i = new Intent(this,RegisterActivity.class);
                startActivity(i);
                break;
        }
    }
}
