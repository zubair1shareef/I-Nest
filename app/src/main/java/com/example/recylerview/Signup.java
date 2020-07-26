package com.example.recylerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText emial,name,password,password1;
    String image="address here",user;
    String namee,email,city="Hyderabad",pass1,pass2;
    FirebaseUser FirebaseUser;
    DatabaseReference reff;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    FirebaseAuth fAuth;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emial=(EditText) findViewById(R.id.emails);
        name=(EditText) findViewById(R.id.fullname);
        password=(EditText) findViewById(R.id.passwords);
        password1=(EditText) findViewById(R.id.password2s);


    }

    public void tostring()
    {
        namee=name.getText().toString();
        email=emial.getText().toString().trim();
        pass1=password.getText().toString();
        pass2=password1.getText().toString();

    }

    public void signup(View v)
    {

        tostring();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, pass1)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Users");
                        Signupdata data=new Signupdata(namee,email,city,image);

                        myRef.child (mAuth.getUid ()).setValue(data);


                    } else {

                        Toast.makeText(Signup.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }

                    // ...
                }
            });

    }
}
