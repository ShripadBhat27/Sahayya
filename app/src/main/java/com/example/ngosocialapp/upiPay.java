package com.example.ngosocialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class upiPay extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_pay);
        TextInputEditText amount=findViewById(R.id.UPIAmount);
        TextInputEditText upiid=findViewById(R.id.UPInum);
        Intent j=getIntent();
        String str=j.getStringExtra("ngoname");
        Button upibtn=findViewById(R.id.UPIPay);
        String user,ngo;
        ngo=str;
        fAuth= FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance().getReference("transaction");
        user=fAuth.getUid();
        Toast.makeText(getApplicationContext(),"UPI",Toast.LENGTH_SHORT).show();
        upibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(amount.getText().toString().isEmpty()==true || upiid.getText().toString().isEmpty()==true)
                {
                    Toast.makeText(getApplicationContext(),"Few filed are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    transaction tra=new transaction(user,ngo,amount.getText().toString());
                    databaseUsers.child(user).push().setValue(tra);
                    databaseUsers.child(ngo).push().setValue(tra);
                    Intent j=new Intent(getApplicationContext(),splashAfterTran.class);
                    startActivity(j);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
            }
        });
    }
}