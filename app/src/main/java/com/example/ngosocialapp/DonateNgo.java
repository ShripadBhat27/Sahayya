package com.example.ngosocialapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
The most important part of app and only point of relevence of app with Zeta hackathon
 */
public class DonateNgo extends Fragment {
    View parentHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder  = inflater.inflate(R.layout.donate_to_ngo, container, false);

        

        return parentHolder;
    }
}