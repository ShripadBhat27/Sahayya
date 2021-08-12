package com.example.ngosocialapp.AdapterClasses;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.ngosocialapp.Post;
import com.example.ngosocialapp.R;
import com.example.ngosocialapp.ngoProfile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FeedPostAdapter extends RecyclerView.Adapter<FeedPostAdapter.viewHolder> {
    Context mContext;
    ArrayList<Post> list;

    public FeedPostAdapter(Context mContext, ArrayList<Post> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public FeedPostAdapter.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.feed_post,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedPostAdapter.viewHolder holder, int position) {
        Post post = list.get(position);
        Uri ur;
        ur=Uri.parse(post.getPostUrl());
        Glide.with(mContext).load(ur).into(holder.postMedia);
        holder.caption.setText(post.getCaption());
        holder.ngoName.setText(post.getNgoName());
        holder.ngoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                
                Fragment myFragment = new ngoProfile();
                Bundle bundle = new Bundle();
                bundle.putString("sending_from_feed",post.getNgoName()); // Put anything what you want

                myFragment.setArguments(bundle);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public static class viewHolder extends RecyclerView.ViewHolder {
            ImageView  postMedia;
            TextView caption,ngoName;

            public viewHolder(@NonNull View itemView){
                super(itemView);

                ngoName = itemView.findViewById(R.id.ngo_name_feed_post);
//                caption = itemView.findViewById(R.id.post_desc);
                caption=itemView.findViewById(R.id.feed_post_caption_comments);
                postMedia =itemView.findViewById(R.id.feed_post_pic);
            }
    }
}
