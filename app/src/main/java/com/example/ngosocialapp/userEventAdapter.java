package com.example.ngosocialapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ngosocialapp.Post;
import com.example.ngosocialapp.R;
import com.example.ngosocialapp.event;
import com.example.ngosocialapp.ngoProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class userEventAdapter extends RecyclerView.Adapter<userEventAdapter.viewHolder> {
    Context mContext;
    ArrayList<event> list;

    public userEventAdapter(Context mContext, ArrayList<event> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public userEventAdapter.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.user_event_item,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull userEventAdapter.viewHolder holder, int position) {
        event e = list.get(position);
        int colourcode=getRandomColor();
        holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(colourcode,null));

        holder.ungo.setText(e.getNgoName());
        holder.uname.setText(e.getName());
        holder.udec.setText(e.getDesc());
        holder.udate.setText(e.getDate());
        holder.usendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(mContext,"Hello",Toast.LENGTH_SHORT).show();
                String searched_ngo = e.getNgoName();
                DatabaseReference searching_ngo= FirebaseDatabase.getInstance().getReference("NGO_by_name");
                searching_ngo.child(searched_ngo).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        solve(snapshot.getValue(String.class),searched_ngo,e);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                //  ApplicationController.getInstance().addToRequestQueue(getRequest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView ungo,uname,udec,udate;
        ImageButton usendInfo;

        public viewHolder(@NonNull View itemView){
            super(itemView);

            ungo = itemView.findViewById(R.id.userEventNgos);
            uname=itemView.findViewById(R.id.userEventName);
            udec =itemView.findViewById(R.id.userEventDesc);
            udate=itemView.findViewById(R.id.userEventDate);
            usendInfo=itemView.findViewById(R.id.userReg);
        }
    }
    private int getRandomColor()
    {
        List<Integer> colorcode=new ArrayList<>();
        colorcode.add(R.color.gray);
        colorcode.add(R.color.pink);
        colorcode.add(R.color.lightgreen);
        colorcode.add(R.color.skyblue);
        colorcode.add(R.color.color1);
        colorcode.add(R.color.color2);
        colorcode.add(R.color.color3);

        colorcode.add(R.color.color4);
        colorcode.add(R.color.color5);
        colorcode.add(R.color.green);

        Random random=new Random();
        int number=random.nextInt(colorcode.size());
        return colorcode.get(number);



    }
    void solve(String uidi,String searched_ngo,event e){
        DatabaseReference geting_ngo= FirebaseDatabase.getInstance().getReference("NGO").child(uidi);
        geting_ngo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                whatAPI(snapshot.getValue(NGO.class),e);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void whatAPI(NGO value, event e)
    {
                RequestQueue queue = Volley.newRequestQueue(mContext);
                String url ="https://rapidapi.rmlconnect.net/wbm/v1/message";
                StringRequest getRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Log.d("accessToken:",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(mContext, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(mContext, "API working", Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(mContext, "API error", Toast.LENGTH_SHORT).show();
                                Log.i("volley",volleyError.toString());
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
                        String str=new String();
                        String msg="Greetings from Sahayya !! 😇\\n \\n✨ A kind gesture can reach a wound that only compassion can heal ✨ \\n \\n🔴Reminder🔴 \\n💁‍♂️NGO : " +value.getName()+"\\n🎫Event Name:"+e.getName()+ "\\n🚩About Event :" + e.getDesc()+ "\\n📅Date:"+e.getDate() + "\\n🔗For registration👇:\\n"+ value.getWebsite()+ "\\n📱For queries contact:\\n"+"Phone no:"+value.getPhone()+"\\n"+"Email id:"+value.getEmail();
                        str="{\"phone\":\"+917972283169\",\"text\":\""+msg+"\"}";
                        //str="{\"phone\":\"+917972283169\",\"media\":{\"type\":\"media_template\",\"template_name\":\"order_place\",\"lang_code\":\"en\",\"body\":[{\"text\":\"Shriharsh\"},{\"text\":\"Modizindabad\"},{\"text\":\"Modi\"},{\"text\":\"Modi2\"}]}}";
                        //str="{\"applicationID\":\"7969970\",\"formID\":\"teamvirtualcoders12121\",\"ifiID\":140793,\"spoolID\":\"3deb5a70-311c-11ea-978f-2e728ce88125\",\"status\":\"APPROVED\",\"individualID\":\"ae4a9a09-d800-4400-8b86-f4afc0d6da5d\",\"applicationType\":\"REAL\",\"salutation\":\"Ms\",\"firstName\":\"Atharv\",\"middleName\":\"M\",\"lastName\":\"Nhavkar\",\"profilePicURL\":\"\",\"dob\":{\"year\":1992,\"month\":10,\"day\":25},\"gender\":\"\",\"mothersMaidenName\":\"\",\"vectors\":{\"4985db6b-e483-48e2-b667-efba55652c21\":{\"type\":\"p\",\"value\":\"+919765391925\",\"aetherID\":\"4985db6b-e483-48e2-b667-efba55652c21\",\"isVerified\":false},\"d3b3c105-0794-4dfd-acae-d2fdd127b060\":{\"type\":\"pan\",\"value\":\"SampleuserPan\",\"aetherID\":\"d3b3c105-0794-4dfd-acae-d2fdd127b060\",\"isVerified\":false}},\"tags\":{\"a8c0a1f1-5374-4982-8375-0933d69f800c\":{\"type\":\"vbo-id\",\"value\":\"d73419fb-ae48-4b67-a764-e23e8752071b\"}},\"kycDetails\":{\"kycStatus\":\"MINIMAL\",\"updateTime\":\"2021-08-26T15:11:05.000+05:30\",\"expiryTime\":\"2120-08-02T15:11:05.000+05:30\",\"kycStatusPostExpiry\":\"MINIMAL\",\"authType\":\"PAN\",\"authData\":{\"PAN\":\"SampleuserPan\"},\"kycAttributes\":{\"pan\":\"SampleuserPan\",\"kycType\":\"MINIMAL\",\"authType\":\"PAN\"}},\"customFields\":{\"entity_id\":\"ABCD0001\",\"isAtmEnabled\":true},\"createdAt\":\"2021-08-26T15:11:05.000+05:30\",\"updatedAt\":\"2021-08-26T15:11:05.000+05:30\",\"source\":\"Fusion\",\"statusDetails\":{}}{\"ifiID\": \"140793\",\"formID\": \"{{formID}}\",\"applicationType\": \"CREATE_ACCOUNT_HOLDER_01\",\"spoolID\": \"3deb5a70-311c-11ea-978f-2e728ce88125\",\"individualType\": \"REAL\",\"salutation\": \"Ms\",\"firstName\": \"{{firstName}}\",\"middleName\": \"{{midName}}\",\"lastName\": \"{{lastName}}\",\"profilePicURL\": \"\",\"dob\": {\"year\": 1992,\"month\": 10,\"day\": 25},\"gender\": \"\",\"mothersMaidenName\": \"\",\"kycDetails\": {\"kycStatus\": \"MINIMAL\",\"kycStatusPostExpiry\": \"KYC_EXPIRED\",\"kycAttributes\": {},\"authData\": {\"PAN\": \"{{PANCardNo.}}\"},\"authType\": \"PAN\"},\"vectors\": [{\"type\": \"p\",\"value\": \"+91{{10DigitMobileNo.}}\",\"isVerified\": true}],\"pops\": [],\"customFields\": {\"entity_id\": \"ABCD0001\",\"isAtmEnabled\":true}}";
                        byte[] byteArrray = str.getBytes();
                        return byteArrray;

                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization","617bf1c2245383001100f7da");
                        return headers;
                    }

                };
                queue.add(getRequest);

    }

}
