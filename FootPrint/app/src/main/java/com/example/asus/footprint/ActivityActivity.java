package com.example.asus.footprint;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }

    public void imgButtonClick_process(View view){
        Intent intent = new Intent(ActivityActivity.this, ProcessActivity.class);
        startActivity(intent);
    }

    public void imgButtonClick_activity(View view){

    }

    public void imgButtonClick_plan(View view){
        Intent intent = new Intent(ActivityActivity.this, PlanActivity.class);
        startActivity(intent);
    }

    public void imgButtonClick_profile(View view){
        Intent intent = new Intent(ActivityActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

}


