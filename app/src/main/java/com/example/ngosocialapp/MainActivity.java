package com.example.ngosocialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    DatabaseReference databaseReference;
     int flg;


    /*
     * This isNGO variable related work performeed for:
     * - hone (feed -> firebase recyclerview)
     * - search
     * - profile => depending on NGO and User
     *
     * If a user want use app he should get only 3 items in bottom nav bar
     * If a NGO want to use app, they should get 4 items/buttons in bottom nav bar
     * - add new post will  be additional
     * */
    /*
     * Approch => this isNGO will be from firebase database about user. and made static
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("TypeOfUser").child(userId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                flg=snapshot.getValue(Integer.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // to change actionbar color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#0F21C5")));
        // change color of notificar bar of mobile (where battery is shown)
        getWindow().setStatusBarColor(getResources().getColor(R.color.notification_bar_color));

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();
        }

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

//                    if (flg==0) {
//                        bottomNav.getMenu().getItem(R.id.add_new_post_nav_btn).setTitle("Volunteer");
//                    }
                    switch (item.getItemId()) {
                        case R.id.home_nav_btn:
                            selectedFragment = new home();
                            break;
                        case R.id.search_nav_btn:
                            selectedFragment = new search_fragment();
                            break;
                        case R.id.profile_nav_btn:
                            if(flg == 0) {
                                selectedFragment = new Profile_fragment();
//                                Toast.makeText(getApplicationContext(), " profile fragment called ", Toast.LENGTH_SHORT).show();


                            }
                            else {
//                                Toast.makeText(getApplicationContext(), " NGO profile  fragment called ", Toast.LENGTH_SHORT).show();
                                selectedFragment = new ngoprofile_fragment();
                            }
                            break;
                        case R.id.add_new_post_nav_btn:
                            if (flg==1) {
                                selectedFragment = new NewPost_fragment();
                                break;
                            }
                            else
                            {
                                selectedFragment = new eventUser();
                                break;
                            }

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }





            };
}