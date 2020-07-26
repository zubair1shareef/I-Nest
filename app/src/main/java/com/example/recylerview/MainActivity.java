package com.example.recylerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseRecyclerOptions<Infom> option;
    private FirebaseRecyclerAdapter<Infom, Myviewholder> adapter;
    FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    String na,ro,de,im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Infom in=new Infom();
        na=in.getName();
        ro=in.getRollno();
        de=in.getDescp();
        im=in.getImage();



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.world);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.world:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.local:
                        startActivity(new Intent(getApplicationContext(),Local.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),Search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                finish();


                return false;
            }
        });







        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refe=FirebaseDatabase.getInstance().getReference().child("Rec");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(MainActivity.this,"loading...",
                Toast.LENGTH_LONG).show();
        option=new FirebaseRecyclerOptions.Builder<Infom>().setQuery(refe,Infom.class).build();
        adapter=new FirebaseRecyclerAdapter<Infom, Myviewholder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull Myviewholder myviewholder, int i, @NonNull Infom infom) {
                 myviewholder.name.setText(infom.getName());
                 myviewholder.rollno.setText(infom.getRollno());
                 myviewholder.desp.setText(infom.getDescp());
                 Picasso.get().load(infom.getImage()).into(myviewholder.imageView);
            }

            @NonNull
            @Override
            public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlayout,parent,false);
                return new Myviewholder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


        mAuth = FirebaseAuth.getInstance();

        Toast.makeText(MainActivity.this,mAuth.getUid (),
                Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onBackPressed() {
        finish();
    }
    public void add(View v)
    {
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
}
