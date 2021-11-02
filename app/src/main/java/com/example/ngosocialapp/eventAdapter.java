package com.example.ngosocialapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class eventAdapter extends FirebaseRecyclerAdapter<event,eventAdapter.MyviewHolder>
{
    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public eventAdapter(@NonNull FirebaseRecyclerOptions<event> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull eventAdapter.MyviewHolder holder, int position, @NonNull event model) {
        event fed=model;
        holder.ename.setText(" Event name : "+fed.getName());
        holder.edecp.setText(" Event Description: "+fed.getDesc());
        int colourcode=getRandomColor();
        holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(colourcode,null));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(context,showEventDetails.class));
                context.startActivity(new Intent(context,showEventDetails.class));
            }
        });
    }

    @NonNull
    @Override
    public eventAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.event_item,parent,false);
        return new MyviewHolder(v);
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder
    {
        TextView ename,edecp;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            ename=itemView.findViewById(R.id.uEventNgo);
            edecp=itemView.findViewById(R.id.uEventDesc);
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
