package com.example.recylerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v)
    {
        Intent intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
    public void signupp(View v)
    {
        Intent intent=new Intent(Login.this,Signup.class);
        startActivity(intent);
    }
}
