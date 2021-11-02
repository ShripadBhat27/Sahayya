package com.example.ngosocialapp;

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
import com.example.ngosocialapp.event;
import com.example.ngosocialapp.ngoProfile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView ungo,uname,udec,udate;

        public viewHolder(@NonNull View itemView){
            super(itemView);

            ungo = itemView.findViewById(R.id.userEventNgos);
            uname=itemView.findViewById(R.id.userEventName);
            udec =itemView.findViewById(R.id.userEventDesc);
            udate=itemView.findViewById(R.id.userEventDate);
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

}
