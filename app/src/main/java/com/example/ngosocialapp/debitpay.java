package com.example.ngosocialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class debitpay extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference databaseUsers,databaseUser2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debitpay);
        TextInputEditText amount=findViewById(R.id.debAmount);
        TextInputEditText creditnum=findViewById(R.id.debNum);
        TextInputEditText expdate=findViewById(R.id.debExp);
        TextInputEditText cvv=findViewById(R.id.debCVV);
        Intent j=getIntent();
        String str=j.getStringExtra("ngoname");
        String user,ngo;
        ngo=str;
        fAuth= FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance().getReference("transaction");
        user=fAuth.getUid();
        Toast.makeText(getApplicationContext(),"debit",Toast.LENGTH_SHORT).show();
        Button comtrabtn=findViewById(R.id.depPay);
        comtrabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(amount.getText().toString().isEmpty()==true || creditnum.getText().toString().isEmpty()==true ||
                        expdate.getText().toString().isEmpty()==true || cvv.getText().toString().isEmpty()==true
                )
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