package com.example.asus.footprint;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 *
 */

public class ActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }

    public void btnProcess(View view){
        Intent intent = new Intent(ActivityActivity.this, ProcessActivity.class);
        startActivity(intent);
    }

    public void btnGoToActivity(View view){

    }

    public void btnPlan(View view){
        Intent intent = new Intent(ActivityActivity.this, PlanActivity.class);
        startActivity(intent);
    }

    public void btnProfile(View view){
        Intent intent = new Intent(ActivityActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

}


