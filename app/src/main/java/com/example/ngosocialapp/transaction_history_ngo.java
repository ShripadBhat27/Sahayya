package com.example.ngosocialapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngosocialapp.ModelClasses.TransactionEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link transaction_history_ngo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class transaction_history_ngo extends Fragment {


    DatabaseReference userref;
    private RecyclerView rv;
    private List<TransactionEntry> transactionEntries;
    private CustomAdapter customAdapter;
    View placeholder;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public transaction_history_ngo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment transaction_history_ngo.
     */
    // TODO: Rename and change types and number of parameters
    public static transaction_history_ngo newInstance(String param1, String param2) {
        transaction_history_ngo fragment = new transaction_history_ngo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String key = "Shri";
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            key=bundle.getString("sending_for_transaction");
        }

        placeholder = inflater.inflate(R.layout.fragment_transactionhistory_fragment, container, false);
        rv=placeholder.findViewById(R.id.transactionRecyclerView);
        rv.setHasFixedSize(true);
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        userref = FirebaseDatabase.getInstance().getReference().child("transaction").child(key);

        transactionEntries = new ArrayList<>();


        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                transactionEntries.clear();
                for(DataSnapshot it : snapshot.getChildren())
                {
                    transaction t = it.getValue(transaction.class);
                    String id = it.getKey();
                    int ammount = Integer.parseInt( t.getAmount());
                    String ngo = t.getDonar();
                    String donor_id = t.getDonar();
                    DatabaseReference getting_donor_name=FirebaseDatabase.getInstance().getReference("Donors").child(donor_id).child("name");
                    getting_donor_name.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String Donor_name = "Another NGO";
                            Donor_name = snapshot.getValue(String.class);
                            TransactionEntry new_transaction_entry = new TransactionEntry(ammount,Donor_name,id);
                            transactionEntries.add(new_transaction_entry);
                            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                            customAdapter=new CustomAdapter(getActivity(),transactionEntries);
                            rv.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return placeholder;    }
}