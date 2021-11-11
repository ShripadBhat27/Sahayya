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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


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
                            sendMail(u1);
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

    private void sendMail(Donor curUser)
    {
            //  Toast.makeText(mContext,"Hello",Toast.LENGTH_SHORT).show();
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url ="https://rapidemail.rmlconnect.net/v1.0/messages/sendMail";
            StringRequest getRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    // Log.d("accessToken:",response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        //Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(getApplicationContext(), "API working", Toast.LENGTH_SHORT).show();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
                            //    Log.d("error:",volleyError.toString());
                        }
                    }){


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();
//                                               params.put("grant_type","authorization_code");
//                                               params.put("code",preference.getGrantCode());
//                                               params.put("client_id", Resources.CLIENT_ID);
//                                               params.put("client_secret",Resources.CLIENT_SECRET);
//                                               params.put("scope","scope_test");
//                                               params.put("state","state_test");
//                                               params.put("redirect_uri",Resources.REDIRECT_URI );
                    return params;

                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    //String str=new String();
                    //String mail=new String();
                    String subject = "Registration Successful!!!!";
                    String email = curUser.getEmail();
                    String normaltxt="<img src=\\\"https://firebasestorage.googleapis.com/v0/b/ngosupport-70139.appspot.com/o/Logo.jpg?alt=media&token=f79586fc-793d-404b-8414-454a9f90bca4\\\" width=\\\"200\\\" height=\\\"200\\\">";
                    String mailcontent= "Hi "+curUser.getName()+",<br>Great news! Your Sahayya account is now ready to use! Your new account comes with access to all services provided by the app. <br>We are looking forward to the journey of making an incredible impact. <br>Cheers, <br>Team Sahayya<br><br><br>";
                    mailcontent=mailcontent+normaltxt;
                    //String mailcontent = "Hi,<br>"+curUser.getName()+" donation to "+Curtra.getNGO()+" of Rs"+Curtra.getAmount()+" is successful !! <br><br>Your transaction ID is "+traID+".<br><br>Thanks to be part of journey and helping out helping hands ! We hope to see you again!!!"+reg;
                    String str="{\"owner_id\":\"97123145\",\"token\":\"YHYk5hLeZ6pfXpHhmmegT3c3\",\"smtp_user_name\":\"smtp56482825\",\"message\":{\"html\":\""+ mailcontent +"\",\"text\":\"" + subject +"\",\"subject\":\"" + subject + "\",\"from_email\":\"noreply@rapidemail.rmlconnect.net\",\"from_name\":\"Sahayya\",\"to\":[{\"email\":\""+ email +"\",\"name\":\""+curUser.getName()+"\",\"type\":\"to\"}],\"headers\":{\"Reply-To\":\"noreply@rapidemail.rmlconnect.net\",\"X-Unique-Id\":\"id\"},\"attachments\":[{\"type\":\"text/plain\",\"name\":\"myfile.txt\",\"content\":\"PleaseuseBase64encryptioncodehere\"}],\"images\":[{\"type\":\"image/png\",\"name\":\"IMAGECID\",\"content\":\"ZXhhbXBsZSBmaWxl\"}]}}";

                    //str="{\"owner_id\":\"97123145\",\"token\":\"YHYk5hLeZ6pfXpHhmmegT3c3\",\"smtp_user_name\":\"smtp56482825\",\"message\":{\"html\":\"ExampleHTMLcontent\",\"text\":\"hipiyushsecondtest\",\"subject\":\"examplesubject\",\"from_email\":\"noreply@rapidemail.rmlconnect.net\",\"from_name\":\"ExampleName\",\"to\":[{\"email\":\"atharvnhavkar5@gmail.com\",\"name\":\"piyushkhurud\",\"type\":\"to\"}],\"headers\":{\"Reply-To\":\"noreply@rapidemail.rmlconnect.net\",\"X-Unique-Id\":\"id\"},\"attachments\":[{\"type\":\"text/plain\",\"name\":\"myfile.txt\",\"content\":\"PleaseuseBase64encryptioncodehere\"}],\"images\":[{\"type\":\"image/png\",\"name\":\"IMAGECID\",\"content\":\"ZXhhbXBsZSBmaWxl\"}]}}";

                    //str="{\"owner_id\":\"97123145\",\"token\":\"YHYk5hLeZ6pfXpHhmmegT3c3\",\"smtp_user_name\":\"smtp56482825\",\"message\":{\"html\":\"ExampleHTMLcontent\",\"text\":\"hipiyushsecondtest\",\"subject\":\"examplesubject\",\"from_email\":\"noreply@rapidemail.rmlconnect.net\",\"from_name\":\"ExampleName\",\"to\":[{\"email\":\"atharvnhavkar5@gmail.com\",\"name\":\"piyushkhurud\",\"type\":\"to\"}],\"headers\":{\"Reply-To\":\"noreply@rapidemail.rmlconnect.net\",\"X-Unique-Id\":\"id\"},\"attachments\":[{\"type\":\"text/plain\",\"name\":\"myfile.txt\",\"content\":\"PleaseuseBase64encryptioncodehere\"}],\"images\":[{\"type\":\"image/png\",\"name\":\"IMAGECID\",\"content\":\"ZXhhbXBsZSBmaWxl\"}]}}";
                    //str="{\"applicationID\":\"7969970\",\"formID\":\"teamvirtualcoders12121\",\"ifiID\":140793,\"spoolID\":\"3deb5a70-311c-11ea-978f-2e728ce88125\",\"status\":\"APPROVED\",\"individualID\":\"ae4a9a09-d800-4400-8b86-f4afc0d6da5d\",\"applicationType\":\"REAL\",\"salutation\":\"Ms\",\"firstName\":\"Atharv\",\"middleName\":\"M\",\"lastName\":\"Nhavkar\",\"profilePicURL\":\"\",\"dob\":{\"year\":1992,\"month\":10,\"day\":25},\"gender\":\"\",\"mothersMaidenName\":\"\",\"vectors\":{\"4985db6b-e483-48e2-b667-efba55652c21\":{\"type\":\"p\",\"value\":\"+919765391925\",\"aetherID\":\"4985db6b-e483-48e2-b667-efba55652c21\",\"isVerified\":false},\"d3b3c105-0794-4dfd-acae-d2fdd127b060\":{\"type\":\"pan\",\"value\":\"SampleuserPan\",\"aetherID\":\"d3b3c105-0794-4dfd-acae-d2fdd127b060\",\"isVerified\":false}},\"tags\":{\"a8c0a1f1-5374-4982-8375-0933d69f800c\":{\"type\":\"vbo-id\",\"value\":\"d73419fb-ae48-4b67-a764-e23e8752071b\"}},\"kycDetails\":{\"kycStatus\":\"MINIMAL\",\"updateTime\":\"2021-08-26T15:11:05.000+05:30\",\"expiryTime\":\"2120-08-02T15:11:05.000+05:30\",\"kycStatusPostExpiry\":\"MINIMAL\",\"authType\":\"PAN\",\"authData\":{\"PAN\":\"SampleuserPan\"},\"kycAttributes\":{\"pan\":\"SampleuserPan\",\"kycType\":\"MINIMAL\",\"authType\":\"PAN\"}},\"customFields\":{\"entity_id\":\"ABCD0001\",\"isAtmEnabled\":true},\"createdAt\":\"2021-08-26T15:11:05.000+05:30\",\"updatedAt\":\"2021-08-26T15:11:05.000+05:30\",\"source\":\"Fusion\",\"statusDetails\":{}}{\"ifiID\": \"140793\",\"formID\": \"{{formID}}\",\"applicationType\": \"CREATE_ACCOUNT_HOLDER_01\",\"spoolID\": \"3deb5a70-311c-11ea-978f-2e728ce88125\",\"individualType\": \"REAL\",\"salutation\": \"Ms\",\"firstName\": \"{{firstName}}\",\"middleName\": \"{{midName}}\",\"lastName\": \"{{lastName}}\",\"profilePicURL\": \"\",\"dob\": {\"year\": 1992,\"month\": 10,\"day\": 25},\"gender\": \"\",\"mothersMaidenName\": \"\",\"kycDetails\": {\"kycStatus\": \"MINIMAL\",\"kycStatusPostExpiry\": \"KYC_EXPIRED\",\"kycAttributes\": {},\"authData\": {\"PAN\": \"{{PANCardNo.}}\"},\"authType\": \"PAN\"},\"vectors\": [{\"type\": \"p\",\"value\": \"+91{{10DigitMobileNo.}}\",\"isVerified\": true}],\"pops\": [],\"customFields\": {\"entity_id\": \"ABCD0001\",\"isAtmEnabled\":true}}";
                    byte[] byteArrray = str.getBytes();
                    return byteArrray;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

            };
            queue.add(getRequest);
            //  ApplicationController.getInstance().addToRequestQueue(getRequest);

    }
}