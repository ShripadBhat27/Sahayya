package com.example.ngosocialapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngosocialapp.AdapterClasses.FeedPostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 This fragment is for details of NGO i.e. when we open a profile other , how it should  be like
 There will be another details activity which will be details for post in the feed and likes and comments.
 */
public class ngoProfile extends Fragment {

    DatabaseReference postDatabase;
    FeedPostAdapter adapter;
    ArrayList<Post> feed_list;
    RecyclerView feed_recyclerView;

    Context mContext;
    ImageView img;


    View placeholder;
    ImageView profile_image;
    EditText email_id, full_name_profile, phone_no, website, account_no, ifsc, editintsa, editaddresss, editsector, editdiscription;
    TextView fullname_field, username_field, donation_amount, no_of_donation;
    Button donate;
    DatabaseReference userref;


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        placeholder = inflater.inflate(R.layout.ngoprofile_fragment, container, false);

        String username ="Shri";
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            username=bundle.getString("sending_from_feed");
        }
        no_of_donation = placeholder.findViewById(R.id.no_of_donation);
        donation_amount = placeholder.findViewById(R.id.donation_amount);
        functionfordata(username);
        DatabaseReference userref2 = FirebaseDatabase.getInstance().getReference().child("NGO_by_name").child(username);
        userref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                function(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//
//        userref = FirebaseDatabase.getInstance().getReference().child("NGO").child(userId);
//        fullname_field = placeholder.findViewById(R.id.fullname_field);
////            fullname_field.setText();
//        username_field = placeholder.findViewById((R.id.username_field));
////            username_field.setText();
//        donation_amount = placeholder.findViewById(R.id.donation_amount);
//        no_of_donation = placeholder.findViewById(R.id.no_of_donation);
//        email_id = placeholder.findViewById(R.id.editTextTextEmailAddress);
//        full_name_profile = placeholder.findViewById(R.id.editTextTextPersonName);
//        phone_no = placeholder.findViewById(R.id.editTextPhone);
//        website = placeholder.findViewById(R.id.website);
//        account_no = placeholder.findViewById(R.id.editText_account_no);
//        ifsc = placeholder.findViewById(R.id.editTextifsc);
//        donate = (Button) placeholder.findViewById(R.id.donate);
//
//
//        editintsa = placeholder.findViewById(R.id.editinstagram);
//        editaddresss = placeholder.findViewById(R.id.editadress);
//        editsector = placeholder.findViewById(R.id.editsector);
//        editdiscription = placeholder.findViewById(R.id.editdescription);
//
//
//        userref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                NGO curr_ngo = snapshot.getValue(NGO.class);
//                fullname_field.setText(curr_ngo.getName());
//                String since = "since " + curr_ngo.getYear();
//                username_field.setText(since);
//                email_id.setText(curr_ngo.getEmail());
//                full_name_profile.setText(curr_ngo.getName());
//                phone_no.setText(curr_ngo.getPhone());
//                website.setText(curr_ngo.getWebsite());
//                account_no.setText(curr_ngo.getAccountNum());
//                ifsc.setText(curr_ngo.getIFSC());
//                editintsa.setText(curr_ngo.getInsta());
//                editaddresss.setText(curr_ngo.getAddress());
//                editsector.setText(curr_ngo.getSector());
//                editdiscription.setText(curr_ngo.getDesc());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//        donate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // piyush cha kaaam
//            }
//        });
//
//
//
//        feed_recyclerView = placeholder.findViewById(R.id.ngo_feed);
//        postDatabase = FirebaseDatabase.getInstance().getReference("Posts").child(userId);
//        feed_list = new ArrayList<>();
//        postDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange (@NonNull DataSnapshot snapshot){
//                feed_list.clear();
//                for (DataSnapshot it : snapshot.getChildren()) {
//                    Post p=it.getValue(Post.class);
//                    feed_list.add(p);
//                }
//
//                adapter = new FeedPostAdapter(mContext,feed_list);
//
//                feed_recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//                feed_recyclerView.setAdapter(adapter);
//
////              adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled (@NonNull DatabaseError error){
//
//            }
//
//        });


//
//        donateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // change fragment to donate button
//                // main part of app where API will be used.
//
//            }
//        });

        return placeholder;
    }


    public void function(String userId) {

        userref = FirebaseDatabase.getInstance().getReference().child("NGO").child(userId);
        fullname_field = placeholder.findViewById(R.id.fullname_field);
//            fullname_field.setText();
        username_field = placeholder.findViewById((R.id.username_field));
//            username_field.setText();
        donation_amount = placeholder.findViewById(R.id.donation_amount);
        no_of_donation = placeholder.findViewById(R.id.no_of_donation);
        email_id = placeholder.findViewById(R.id.editTextTextEmailAddress);
        full_name_profile = placeholder.findViewById(R.id.editTextTextPersonName);
        phone_no = placeholder.findViewById(R.id.editTextPhone);
        website = placeholder.findViewById(R.id.website);
        account_no = placeholder.findViewById(R.id.editText_account_no);
        ifsc = placeholder.findViewById(R.id.editTextifsc);
        donate = (Button) placeholder.findViewById(R.id.donate);


        editintsa = placeholder.findViewById(R.id.editinstagram);
        editaddresss = placeholder.findViewById(R.id.editadress);
        editsector = placeholder.findViewById(R.id.editsector);
        editdiscription = placeholder.findViewById(R.id.editdescription);


        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NGO curr_ngo = snapshot.getValue(NGO.class);
                fullname_field.setText(curr_ngo.getName());
                String since = "since " + curr_ngo.getYear();
                username_field.setText(since);
                email_id.setText(curr_ngo.getEmail());
                full_name_profile.setText(curr_ngo.getName());
                phone_no.setText(curr_ngo.getPhone());
                website.setText(curr_ngo.getWebsite());
                account_no.setText(curr_ngo.getAccountNum());
                ifsc.setText(curr_ngo.getIFSC());
                editintsa.setText(curr_ngo.getInsta());
                editaddresss.setText(curr_ngo.getAddress());
                editsector.setText(curr_ngo.getSector());
                editdiscription.setText(curr_ngo.getDesc());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // piyush cha kaaam

                Intent i=new Intent(getActivity(),DonerPayment.class);
                i.putExtra("ngoname",full_name_profile.getText().toString());
                startActivity(i);
            }
        });


        feed_recyclerView = placeholder.findViewById(R.id.ngo_feed);
        postDatabase = FirebaseDatabase.getInstance().getReference("Posts").child(userId);
        feed_list = new ArrayList<>();
        postDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feed_list.clear();
                for (DataSnapshot it : snapshot.getChildren()) {
                    Post p = it.getValue(Post.class);
                    feed_list.add(p);
                }

                adapter = new FeedPostAdapter(mContext, feed_list);

                feed_recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                feed_recyclerView.setAdapter(adapter);

//              adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }



    void functionfordata(String userId){

        DatabaseReference fortransactions = FirebaseDatabase.getInstance().getReference().child("transaction").child(userId);
        int totalammount =0 , nos=0;
        fortransactions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalammount =0 , nos=0;
                for(DataSnapshot it : snapshot.getChildren()) {
                    transaction t = it.getValue(transaction.class);
                    String id = it.getKey();
                    int ammount = Integer.parseInt(t.getAmount());
                    totalammount+=ammount;
                    nos = nos +1;
                }
                no_of_donation.setText(String.valueOf(nos));
                donation_amount.setText(String.valueOf(totalammount));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}


