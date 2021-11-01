package com.example.ngosocialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ngoEventDetails extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference database;
    private eventAdapter adapter;
    private FirebaseAuth auth;
    private FloatingActionButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_event_details);
        Intent j=getIntent();
        String cur=j.getStringExtra("name");
        recyclerView=findViewById(R.id.showEventNgo);
        auth=FirebaseAuth.getInstance();
        database=  FirebaseDatabase.getInstance().getReference("event").child(auth.getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<event> options=
                new FirebaseRecyclerOptions.Builder<event>()
                        .setQuery(database,event.class)
                        .build();
        adapter=new eventAdapter(options,this);
        recyclerView.setAdapter(adapter);
        add=findViewById(R.id.addEventNgo);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getApplicationContext(),CreateEvent.class);
                i.putExtra("name",cur);
                startActivity(i);
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }
}