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

import com.example.ngosocialapp.AdapterClasses.FeedPostAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class eventUser extends Fragment {

    Context mcontext;
    DatabaseReference eventdatabase;
    userEventAdapter ueventadapter;
    ArrayList<event> eventlist;
    RecyclerView eventview;
    View parentHolder;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mcontext=context;
    }

    public eventUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentHolder= inflater.inflate(R.layout.fragment_event_user, container, false);

        eventview = parentHolder.findViewById(R.id.userEventList);
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        eventdatabase = FirebaseDatabase.getInstance().getReference("event");
        eventlist = new ArrayList<>();
        eventdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot){

                for (DataSnapshot it : snapshot.getChildren()) {
                    for (DataSnapshot it2 : it.getChildren()) {
                        event p=it2.getValue(event.class);
                        eventlist.add(p);
                    }


                }

                ueventadapter = new userEventAdapter(mcontext,eventlist);

                eventview.setLayoutManager(new LinearLayoutManager(mcontext));
                eventview.setAdapter(ueventadapter);

//              adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }

        });








        return parentHolder;
    }

}