package com.example.ngosocialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class registerDonor extends AppCompatActivity {
    TextInputEditText name,email,password,age;
    Button register;

    ProgressBar progressBar;
    FirebaseAuth fAuth;
    DatabaseReference databaseUsers,Typeofusers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_donor);
        name=findViewById(R.id.Dname);
        email=findViewById(R.id.Demail);
        password=findViewById(R.id.Dpassword);
        age=findViewById(R.id.D_age);
        register=findViewById(R.id.register_donor);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.DprogressBar);
        databaseUsers= FirebaseDatabase.getInstance().getReference("Donors");
        Typeofusers= FirebaseDatabase.getInstance().getReference("TypeOfUser");

        //check if user is already login
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String mPassword=password.getText().toString();
                String Name=name.getText().toString();
                String Age=age.getText().toString();
                if(TextUtils.isEmpty(Email)){
                    email.setError("EMAIL IS EMPTY!!");
                    return;
                }

                if(TextUtils.isEmpty(mPassword)){
                    password.setError("Password IS EMPTY!!");
                    return;
                }

                if(mPassword.length()<6){
                    password.setError("ENTER 6 or more characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register on firebaSE
                fAuth.createUserWithEmailAndPassword(Email,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(registerDonor.this, "USER CREATED", Toast.LENGTH_SHORT).show();
                            String userID=fAuth.getCurrentUser().getUid();

                            Donor u1=new Donor(Name,Age,Email);

                            databaseUsers.child(userID).setValue(u1);

                            Typeofusers.child(userID).setValue(0);


                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(registerDonor.this, "ERROR ->"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });



    }
}