package com.example.asus.footprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/**
 *
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}


    public void btnSignUp(View view) {

        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void btnContinue(View view) {

        Intent intent = new Intent(MainActivity.this, LoginInActivity.class);
        startActivity(intent);
    }

}
