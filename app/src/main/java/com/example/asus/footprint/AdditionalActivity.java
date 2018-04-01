package com.example.asus.footprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 *
 */

public class AdditionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional);
    }

    public void btnDone(View view) {

        Intent intent = new Intent(AdditionalActivity.this, ProcessActivity.class);
        startActivity(intent);
    }
}
