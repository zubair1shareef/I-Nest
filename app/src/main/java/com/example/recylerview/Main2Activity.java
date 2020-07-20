package com.example.recylerview;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {
EditText name,rollno,decp;
String nam,rol,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=(EditText) findViewById(R.id.Name);
        rollno=(EditText) findViewById(R.id.Rollno);
        decp=(EditText) findViewById(R.id.descp);


    }
    public void push(View v)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Rec");
        nam=name.getText().toString();
        rol=rollno.getText().toString();
        des=decp.getText().toString();
        Infom info=new Infom(nam,rol,des);
        String key = myRef.push().getKey();
        myRef.child(key).setValue(info);





    }
}
