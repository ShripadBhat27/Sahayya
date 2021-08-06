package com.example.ngosocialapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
This fragment is for details of NGO i.e. when we open a profile other , how it should  be like
 There will be another details activity which will be details for post in the feed and likes and comments.
 */
public class ngoProfile extends Fragment {

    Button aboutUsBtn, donateBtn;
    View parentHolder;
    ImageView  ngoProfilePhoto;
    TextView ngoName, ngoHeadline;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentHolder=inflater.inflate(R.layout.ngoprofile_fragment, container, false);
        aboutUsBtn = parentHolder.findViewById(R.id.about_us_btn);
        donateBtn = parentHolder.findViewById(R.id.donate_btn);
        ngoHeadline = parentHolder.findViewById(R.id.ngo_name_ngo_profile_frag);
        ngoName = parentHolder.findViewById(R.id.headline_ngo_profile);
        ngoProfilePhoto = parentHolder.findViewById(R.id.ngo_profile_photo);

        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change fragment to donate button
                // main part of app where API will be used.
                
            }
        });

        return parentHolder;
    }


}