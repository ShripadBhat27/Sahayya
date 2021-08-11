package com.example.ngosocialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NgoBank extends AppCompatActivity {

    TextInputEditText account,reAccount,IFSC,reci;
    FirebaseAuth fAuth;
    DatabaseReference databaseUsers,databaseUser2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_bank);
        account=findViewById(R.id.NgoAccount);
        reAccount=findViewById(R.id.NgoAccountRe);
        IFSC=findViewById(R.id.NgoIFSC);
        reci=findViewById(R.id.NgoaccName);
        fAuth= FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance().getReference("NGO");
        databaseUser2=FirebaseDatabase.getInstance().getReference("NGO_by_name");

        FloatingActionButton firstNext=findViewById(R.id.thirdNext);
        firstNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                NGO u1 = (NGO)i.getSerializableExtra("ngoObj");
                u1.setAccountNum(account.getText().toString());
                u1.setReAccount(reAccount.getText().toString());
                u1.setIFSC(IFSC.getText().toString());
                String userID=fAuth.getCurrentUser().getUid();
                databaseUsers.child(userID).setValue(u1);
                databaseUser2.child(u1.getName()).setValue(userID);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}