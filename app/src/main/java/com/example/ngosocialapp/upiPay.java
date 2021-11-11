package com.example.ngosocialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class upiPay extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference databaseUsers,getUser;
    String mail,traID;
    Donor curUser;

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
        getUser=FirebaseDatabase.getInstance().getReference("Donors").child(user);
        getUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //mail=snapshot.getValue(String.class);
                curUser=snapshot.getValue(Donor.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                    String md=databaseUsers.push().getKey();
                    traID=md;
                    databaseUsers.child(md).setValue(tra);
                    databaseUsers.child(ngo).push().setValue(tra);
                    Intent j=new Intent(getApplicationContext(),splashAfterTran.class);
                    sendEmail(curUser,tra);
                    sendMsg(curUser,tra);
                    startActivity(j);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
            }
        });
    }

    private void sendMsg(Donor curUser, transaction Curtra)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String phoneno = "+917972283169";
        String msgcontent = "Hi,"+curUser.getName()+" donation to "+Curtra.getNGO()+" of Rs"+Curtra.getAmount()+" is successful !! Your transaction ID is "+traID+". Thanks to be part of journey and helping out helping hands ! We hope to see you again!!!";
        String url="https://rapidapi.rmlconnect.net:9443/bulksms/bulksms?username=rapid-SrrR4264010000&password=617bf1c2245383001100f7da&type=0&dlr=1&destination="+ phoneno +"&source=RMLPRD&message=" + msgcontent ;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //let's parse json data
                try {
                    //JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error1", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error2", Toast.LENGTH_SHORT).show();
            }
        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params=new HashMap<String, String>();
////                params.put("data_1_post","Value 1 Data");
////                params.put("data_2_post","Value 2 Data");
////                params.put("data_3_post","Value 3 Data");
////                params.put("data_4_post","Value 4 Data");
//                return params;
//            }
//
//            @Override
//            public Map<String,String> getHeaders() throws AuthFailureError {
//                Map<String,String> params=new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
        };

        requestQueue.add(stringRequest);
    }

    private void sendEmail(Donor curUser,transaction Curtra)
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
                    Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "API working", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
                        Log.d("error:",volleyError.toString());
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
                String subject = "Transaction Successful!!!!";
                String email = curUser.getEmail();
                String mailcontent = "Hi,<br>"+curUser.getName()+" donation to "+Curtra.getNGO()+" of Rs"+Curtra.getAmount()+" is successful !! <br>Your transaction ID is "+traID+".<br>Thanks to be part of journey and helping out helping hands ! We hope to see you again!!!<br>Regards,<br>Team Sahayya";
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