package com.example.ngosocialapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ngosocialapp.AdapterClasses.FeedPostAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class search_fragment extends Fragment {
    Activity referenceActivity;
    View parentHolder;
    Button search_ngo;
    Integer ID =0 ;
    Context mContext; // context of search fragment
    DatabaseReference searching_ngo;
    EditText type_ngo;
    String uidi="ZETA";

    public search_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(@org.jetbrains.annotations.NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        referenceActivity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_search, container, false);
        search_ngo = parentHolder.findViewById(R.id.search_ngo);

        type_ngo=parentHolder.findViewById(R.id.type_ngo);
        searching_ngo= FirebaseDatabase.getInstance().getReference("NGO_by_name");


        search_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searched_ngo = type_ngo.getText().toString();

                searching_ngo.child(searched_ngo).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        solve(snapshot.getValue(String.class),searched_ngo);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });



        return parentHolder;
    }

    void solve(String uidi,String searched_ngo){
        if(uidi!=null) {
            AppCompatActivity activity = (AppCompatActivity) mContext;
            Fragment myFragment = new ngoProfile();
            Bundle bundle = new Bundle();
            bundle.putString("sending_from_feed",searched_ngo);
            myFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
        }
        else
            Toast.makeText(referenceActivity, "No NGO Exists", Toast.LENGTH_SHORT).show();
    }

}