package com.example.ngosocialapp.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ngosocialapp.ModelClasses.NgoProfileAllPhotoModel;
import com.example.ngosocialapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NgoAllPostAdapter extends RecyclerView.Adapter<NgoAllPostAdapter.viewHolder> {
    Context mContext;
    ArrayList<NgoProfileAllPhotoModel> list;

    public NgoAllPostAdapter(Context mContext, ArrayList<NgoProfileAllPhotoModel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public NgoAllPostAdapter.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.feed_post,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NgoAllPostAdapter.viewHolder holder, int position) {
        NgoProfileAllPhotoModel post = list.get(position);
        holder.post_item.setImageURI(post.getNgoPost());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView post_item;
        public viewHolder(@NonNull View itemView){
            super(itemView);
            post_item = itemView.findViewById(R.id.post_item);
        }
    }
}
