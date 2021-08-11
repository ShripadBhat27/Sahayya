package com.example.ngosocialapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ngosocialapp.AdapterClasses.FeedPostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;


public class home extends Fragment {



    DatabaseReference postDatabase;
    FeedPostAdapter adapter;
    ArrayList<Post> feed_list;
    RecyclerView feed_recyclerView;

    View parentHolder;
    Context mContext;
    ImageView img;

    public home() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        mContext = context;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder= inflater.inflate(R.layout.fragment_home, container, false);

        feed_recyclerView = parentHolder.findViewById(R.id.feed);
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        postDatabase = FirebaseDatabase.getInstance().getReference("Posts");
        feed_list = new ArrayList<>();
        postDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot){

                for (DataSnapshot it : snapshot.getChildren()) {
                    for (DataSnapshot it2 : it.getChildren()) {
                        Post p=it2.getValue(Post.class);
                        feed_list.add(p);
                    }


                }

                adapter = new FeedPostAdapter(mContext,feed_list);

                feed_recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                feed_recyclerView.setAdapter(adapter);

//              adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }

        });




//        adapter.notifyDataSetChanged();
//        postDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange (@NonNull DataSnapshot snapshot){
//
//                for (DataSnapshot it : snapshot.getChildren()) {
//                    Post p=it.getValue(Post.class);
//                    feed_list.add(p);
//                }
//                Toast.makeText(mContext, "NO :"+feed_list.size(), Toast.LENGTH_SHORT).show();
//                feed_recyclerView.setHasFixedSize(true);
//
//
//
//              adapter = new FeedPostAdapter(mContext,feed_list);
//              adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled (@NonNull DatabaseError error){
//
//            }
//
//        });



        return parentHolder;
    }
}