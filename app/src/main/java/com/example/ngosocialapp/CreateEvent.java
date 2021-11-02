package com.example.ngosocialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateEvent extends AppCompatActivity {

    EditText eventname,eventdecp,eventdate;
    FloatingActionButton saveevent;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    ProgressBar mprogressbarofcreatenote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Intent i=getIntent();
        String ngoName=i.getStringExtra("name");
        eventname=findViewById(R.id.eventName);
        eventdecp=findViewById(R.id.eventDesc);
        eventdate=findViewById(R.id.eventDate);
        saveevent=findViewById(R.id.addEventbnt);
        saveevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth=FirebaseAuth.getInstance();
                String ename,edecp,edate,engoname;
                ename=eventname.getText().toString();
                edecp=eventdecp.getText().toString();
                edate=eventdate.getText().toString();
                engoname=ngoName;
                event curEvent=new event(engoname,ename,edecp,edate,true);
                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference root = FirebaseDatabase.getInstance().getReference("event").child(firebaseUser.getUid());
                String md=root.push().getKey();
                root.child(md).setValue(curEvent);

                finish();
                startActivity(new Intent(getApplicationContext(),ngoEventDetails.class));
            }
        });


    }

}