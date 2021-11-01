package com.example.ngosocialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class NgoProfileInfo extends AppCompatActivity {

    TextInputEditText desc,web,insta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_profile_info);
        desc=findViewById(R.id.NgoBrief);
        web=findViewById(R.id.NgoWeb);
        insta=findViewById(R.id.NgoInsta);
        FloatingActionButton firstNext=findViewById(R.id.addEventbnt);
        firstNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(desc.getText().toString().isEmpty()==true || web.getText().toString().isEmpty()==true ||
                        insta.getText().toString().isEmpty()==true)
                {
                    Toast.makeText(getApplicationContext(),"Few filed are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = getIntent();
                    NGO u1 = (NGO)i.getSerializableExtra("ngoObj");
                    u1.setDesc(desc.getText().toString());
                    u1.setWebsite(web.getText().toString());
                    u1.setInsta(insta.getText().toString());
                    Intent j=new Intent(getApplicationContext(),NgoBank.class);
                    j.putExtra("ngoObj", (Serializable) u1);
                    startActivity(j);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
    }
}