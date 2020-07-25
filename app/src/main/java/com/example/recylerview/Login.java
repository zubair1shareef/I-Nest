package com.example.recylerview;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button login;
    LinearLayout linearLayout;
    private FirebaseAuth mAuth;
    EditText email,password;
    String Email,Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button) findViewById(R.id.login);
        email=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        linearLayout=(LinearLayout) findViewById(R.id.linearl1);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void login(View v)
    {    Email=email.getText().toString().trim();
         Password=password.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        Intent intent=new Intent(Login.this,MainActivity.class);
        Pair[] pairs=new Pair[2];
        pairs[0]=new Pair<View ,String>(login,"open");
        pairs[1]=new Pair<View ,String>(linearLayout,"layout");
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
        startActivity(intent,options.toBundle());
        finish();

/*
        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent=new Intent(Login.this,MainActivity.class);
                            Pair[] pairs=new Pair[2];
                            pairs[0]=new Pair<View ,String>(login,"open");
                            pairs[1]=new Pair<View ,String>(linearLayout,"layout");
                            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                            startActivity(intent,options.toBundle());
                            finish();
                        } else {


                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });*/






    }

    public void signupp(View v)
    {
        Intent intent=new Intent(Login.this,Signup.class);
        startActivity(intent);

    }

}
