package com.example.ngosocialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;
import com.braintreepayments.cardform.view.SupportedCardTypesView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class creditPay extends AppCompatActivity  {

    FirebaseAuth fAuth;
    DatabaseReference databaseUsers;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_pay);
        TextInputEditText amount=findViewById(R.id.Amount);
        TextInputEditText creditnum=findViewById(R.id.creditNum);
        TextInputEditText expdate=findViewById(R.id.creExp);
        TextInputEditText cvv=findViewById(R.id.creditCVV);
        Intent j=getIntent();
        String str=j.getStringExtra("ngoname");
        Toast.makeText(getApplicationContext(),"credit",Toast.LENGTH_SHORT).show();
        fAuth= FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance().getReference("transaction");
        String user,ngo;
        user=fAuth.getUid();
        ngo=str;
        Button comtrabtn=findViewById(R.id.credpay);
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