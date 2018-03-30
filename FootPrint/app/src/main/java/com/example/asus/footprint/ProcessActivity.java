package com.example.asus.footprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_layout);
    }

    public void imgButtonClick_process(View view){

    }

    public void imgButtonClick_activity(View view){
        Intent intent = new Intent(ProcessActivity.this, ActivityActivity.class);
        startActivity(intent);
    }

    public void imgButtonClick_plan(View view){
        Intent intent = new Intent(ProcessActivity.this, PlanActivity.class);
        startActivity(intent);
    }

    public void imgButtonClick_profile(View view){
        Intent intent = new Intent(ProcessActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
