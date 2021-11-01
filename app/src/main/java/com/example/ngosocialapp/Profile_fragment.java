package com.example.ngosocialapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_fragment extends Fragment {
//    Button btm;

    Context mContext;
    View placeholder;
    ImageView profile_image ;
    EditText email_id,full_name_profile,phone_no,age;
    TextView fullname_field, username_field , donation_amount, no_of_donation ;
    CardView box;
    Button update , logout ,NgoEventAdd;
    DatabaseReference userref;
    DatabaseReference fortransactions ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile_fragment() {
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
     * @return A new instance of fragment Profile_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile_fragment newInstance(String param1, String param2) {
        Profile_fragment fragment = new Profile_fragment();
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

        int flg = 0;

        placeholder =  inflater.inflate(R.layout.profile_fragment, container, false);
//          profile_image = placeholder.findViewById(R.id.profile_image);

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        userref = FirebaseDatabase.getInstance().getReference().child("Donors").child(userId);
        fullname_field = placeholder.findViewById(R.id.fullname_field);
//            fullname_field.setText();
        username_field = placeholder.findViewById((R.id.username_field));
//            username_field.setText();
        donation_amount = placeholder.findViewById(R.id.donation_amount);
        box = placeholder.findViewById(R.id.boxforhistory);
        no_of_donation = placeholder.findViewById(R.id.no_of_donation);
        email_id = placeholder.findViewById(R.id.editTextTextEmailAddress);
        full_name_profile = placeholder.findViewById(R.id.editTextTextPersonName);
        phone_no = placeholder.findViewById(R.id.editTextPhone);
        age = placeholder.findViewById(R.id.Password);
        update = (Button) placeholder.findViewById(R.id.update);
        logout = placeholder.findViewById(R.id.logout);





        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Donor d = snapshot.getValue(Donor.class);
                fullname_field.setText(d.getName());
                username_field.setText(d.getEmail());
                email_id.setText(d.getEmail());
                full_name_profile.setText(d.getName());
                age.setText(d.getAge());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name,sage,email;
                name = full_name_profile.getText().toString();
                email = email_id.getText().toString();
                sage = age.getText().toString();
                Donor newd = new Donor(name,sage,email);
                userref.setValue(newd);
            }
        });

        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment selectedFragment = new transactionhistory_fragment();
                Bundle bundle = new Bundle();
                bundle.putString("sending_for_transaction",userId); // Put anything what you want

                selectedFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(mContext.getApplicationContext(),SelectUserType.class));

            }
        });


        fortransactions = FirebaseDatabase.getInstance().getReference().child("transaction").child(userId);
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


        return placeholder;



    }


    public void toggle(View view){
//        MainActivity.flg = !MainActivity.flg;
        ;
    }


}
