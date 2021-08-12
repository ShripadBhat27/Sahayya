package com.example.ngosocialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class NgoBasicInfo extends AppCompatActivity {
    TextInputEditText phone,sector,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_basic_info);
        sector=findViewById(R.id.NgoSector);
        address=findViewById(R.id.NgoAddress);
        phone=findViewById(R.id.NgoPhone);
        FloatingActionButton firstNext=findViewById(R.id.firstNext);
        //check for inputs
        firstNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sector.getText().toString().isEmpty()==true || address.getText().toString().isEmpty()==true ||
                phone.getText().toString().isEmpty()==true)
                {
                    Toast.makeText(getApplicationContext(),"Few filed are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = getIntent();
                    NGO u1 = (NGO)i.getSerializableExtra("ngoObj");
                    u1.setPhone(phone.getText().toString());
                    u1.setAddress(address.getText().toString());
                    u1.setSector(sector.getText().toString());
                    Intent j=new Intent(getApplicationContext(),NgoProfileInfo.class);
                    j.putExtra("ngoObj", (Serializable) u1);
                    startActivity(j);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }
        });
    }
}