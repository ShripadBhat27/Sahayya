package com.example.ngosocialapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ngosocialapp.AdapterClasses.FeedPostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ngoprofile_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ngoprofile_fragment extends Fragment {





    DatabaseReference postDatabase;
    FeedPostAdapter adapter;
    ArrayList<Post> feed_list;
    RecyclerView feed_recyclerView;

    Context mContext;
    ImageView img;


    View placeholder;
    ImageView profile_image ;
    EditText email_id,full_name_profile,phone_no,website , account_no , ifsc , editintsa ,editaddresss , editsector, editdiscription;
    TextView fullname_field, username_field , donation_amount, no_of_donation ;
    Button update , logout,NgoEventAdd;

    CardView box ;
    DatabaseReference userref;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ngoprofile_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        mContext = context;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ngoprofile_fragment newInstance(String param1, String param2) {
        ngoprofile_fragment fragment = new ngoprofile_fragment();
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
        placeholder =  inflater.inflate(R.layout.profile_ngo_fragment, container, false);

        box = placeholder.findViewById(R.id.boxforhistory);
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        userref = FirebaseDatabase.getInstance().getReference().child("NGO").child(userId);
        fullname_field = placeholder.findViewById(R.id.fullname_field);
//            fullname_field.setText();
        username_field = placeholder.findViewById((R.id.username_field));
//            username_field.setText();
        donation_amount = placeholder.findViewById(R.id.donation_amount);
        no_of_donation = placeholder.findViewById(R.id.no_of_donation);
        email_id = placeholder.findViewById(R.id.editTextTextEmailAddress);
        full_name_profile = placeholder.findViewById(R.id.editTextTextPersonName);
        phone_no = placeholder.findViewById(R.id.editTextPhone);
        website = placeholder.findViewById(R.id.website);
        account_no = placeholder.findViewById(R.id.editText_account_no);
        ifsc = placeholder.findViewById(R.id.editTextifsc);
        update = (Button) placeholder.findViewById(R.id.update);
        logout = (Button) placeholder.findViewById(R.id.logout);

        editintsa = placeholder.findViewById(R.id.editinstagram);
        editaddresss = placeholder.findViewById(R.id.editadress);
        editsector = placeholder.findViewById(R.id.editsector);
        editdiscription = placeholder.findViewById(R.id.editdescription);
        NgoEventAdd=(Button) placeholder.findViewById(R.id.NGOevent);


        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    function(snapshot.getValue(NGO.class));
                    functionfordata(snapshot.getValue(NGO.class).getName());
//                fullname_field.setText(curr_ngo.getName());
//                String since = "since " + curr_ngo.getYear();
//                username_field.setText(since);
//                email_id.setText(curr_ngo.getEmail());
//                full_name_profile.setText(curr_ngo.getName());
//                phone_no.setText(curr_ngo.getPhone());
//                website.setText(curr_ngo.getWebsite());
//                account_no.setText(curr_ngo.getAccountNum());
//                ifsc.setText(curr_ngo.getIFSC());
//                editintsa.setText(curr_ngo.getInsta());
//                editaddresss.setText(curr_ngo.getAddress());
//                editsector.setText(curr_ngo.getSector());
//                editdiscription.setText(curr_ngo.getDesc());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_email_id,s_full_name_profile,s_phone_no,s_website , s_account_no , s_ifsc, s_editintsa ,s_editaddresss , s_editsector, s_editdiscription;
                s_full_name_profile = full_name_profile.getText().toString();
                s_email_id = email_id.getText().toString();
                s_phone_no = phone_no.getText().toString();
                s_website = website.getText().toString();
                s_account_no = account_no.getText().toString();
                s_ifsc = ifsc.getText().toString();
                s_editintsa = editintsa.getText().toString();
                s_editaddresss = editaddresss.getText().toString();
                s_editsector = editsector.getText().toString();
                s_editdiscription = editdiscription.getText().toString();

                userref.child("name").setValue(s_full_name_profile);
                userref.child("email").setValue(s_email_id);
                userref.child("phone").setValue(s_phone_no);
                userref.child("website").setValue(s_website);
                userref.child("website").setValue(s_website);
                userref.child("accountNum").setValue(s_account_no);
                userref.child("reAccount").setValue(s_account_no);
                userref.child("IFSC").setValue(s_ifsc);
                userref.child("desc").setValue(s_editdiscription);
                userref.child("insta").setValue(s_editintsa);
                userref.child("sector").setValue(s_editsector);
                userref.child("address").setValue(s_editaddresss);

            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(mContext.getApplicationContext(),SelectUserType.class));


//                Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
//                Fragment selectedFragment = new ngoProfile();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        selectedFragment).commit();
            }
        });
        NgoEventAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String ngoName=fullname_field.getText().toString();
                Intent i=new Intent(mContext.getApplicationContext(),ngoEventDetails.class);
                i.putExtra("name",ngoName);
                startActivity(i);
            }
        });



        feed_recyclerView = placeholder.findViewById(R.id.ngo_feed);
        postDatabase = FirebaseDatabase.getInstance().getReference("Posts").child(userId);
        feed_list = new ArrayList<>();
        postDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot){
                feed_list.clear();
                for (DataSnapshot it : snapshot.getChildren()) {
                        Post p=it.getValue(Post.class);
                        feed_list.add(p);
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





        return placeholder;
    }

    void function(NGO curr_ngo){

        fullname_field.setText(curr_ngo.getName());
        String since = "since " + curr_ngo.getYear();
        username_field.setText(since);
        email_id.setText(curr_ngo.getEmail());
        full_name_profile.setText(curr_ngo.getName());
        phone_no.setText(curr_ngo.getPhone());
        website.setText(curr_ngo.getWebsite());
        account_no.setText(curr_ngo.getAccountNum());
        ifsc.setText(curr_ngo.getIFSC());
        editintsa.setText(curr_ngo.getInsta());
        editaddresss.setText(curr_ngo.getAddress());
        editsector.setText(curr_ngo.getSector());
        editdiscription.setText(curr_ngo.getDesc());
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new transaction_history_ngo();
                Bundle bundle = new Bundle();
                bundle.putString("sending_for_transaction",curr_ngo.getName()); // Put anything what you want

                selectedFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
            }
        });

    }


    void functionfordata(String userId){

        DatabaseReference fortransactions = FirebaseDatabase.getInstance().getReference().child("transaction").child(userId);
        int totalammount =0 , nos=0;
        fortransactions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalammount =0 , nos=0;
                for(DataSnapshot it : snapshot.getChildren()) {
                    transaction t = it.getValue(transaction.class);
                    String id = it.getKey();
                    int ammount = Integer.parseInt(t.getAmount());
                    totalammount+=ammount;
                    nos = nos +1;
                }
                no_of_donation.setText(String.valueOf(nos));
                donation_amount.setText(String.valueOf(totalammount));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}