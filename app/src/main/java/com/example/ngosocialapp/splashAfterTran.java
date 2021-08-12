package com.example.ngosocialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class splashAfterTran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_after_tran);
        LottieAnimationView lottieAnimationView1,lottieAnimationView2;
        lottieAnimationView1=findViewById(R.id.animaMone);
        lottieAnimationView2=findViewById(R.id.homeani);
        lottieAnimationView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(j);
                finish();
            }
        });
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}