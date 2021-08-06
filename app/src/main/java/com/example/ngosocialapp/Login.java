package com.example.ngosocialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextInputEditText Lemail,Lpassword;
    Button login_button;
    ProgressBar progressBar_login;
    FirebaseAuth fAuth;
    TextView newRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Lemail=findViewById(R.id.Lemail);
        Lpassword=findViewById(R.id.Lpassword);
        login_button=findViewById(R.id.login_button);
        progressBar_login=findViewById(R.id.progressBar_login);
        newRegister=findViewById(R.id.newRegister);


        fAuth=FirebaseAuth.getInstance();


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=Lemail.getText().toString();

                String Password=Lpassword.getText().toString();

                if(TextUtils.isEmpty(Email)){
                    Lemail.setError("EMAIL IS EMPTY!!");
                    return;
                }

                if(TextUtils.isEmpty(Password)){
                    Lpassword.setError("Password IS EMPTY!!");
                    return;
                }

                if(Password.length()<6){
                    Lpassword.setError("ENTER 6 or more characters");
                    return;
                }

                progressBar_login.setVisibility(View.VISIBLE);
                //Authenticate
                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "LOGIN In Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Error ->"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SelectUserType.class));
            }
        });
    }
}