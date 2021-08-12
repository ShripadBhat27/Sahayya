package com.example.ngosocialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectUserType extends AppCompatActivity {
    ImageView ngo_select,donor_select;
    Button select_type;
    FirebaseAuth fAuth;
    TextView take_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        ngo_select=findViewById(R.id.ngo_select);
        donor_select=findViewById(R.id.donor_select);
        select_type=findViewById(R.id.select_type);
        take_to_login=findViewById(R.id.take_to_login);

        take_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        fAuth= FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        ngo_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ngo_select.getAlpha()==0.75){
                    ngo_select.setAlpha(1.0f);
                }
                else
                    ngo_select.setAlpha(0.75f);
            }
        });

        donor_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(donor_select.getAlpha()==0.75){
                   donor_select.setAlpha(1.0f);
                }
                else
                    donor_select.setAlpha(0.75f);
            }
        });

        select_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ngo_select.getAlpha()==1){
                    startActivity(new Intent(getApplicationContext(),registerNGO.class));


                }
                else if(donor_select.getAlpha()==1){
                    startActivity(new Intent(getApplicationContext(),registerDonor.class));
                }
                else
                    Toast.makeText(SelectUserType.this, "Please Select Appropriate Type!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}