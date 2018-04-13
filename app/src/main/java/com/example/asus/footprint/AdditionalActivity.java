package com.example.asus.footprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdditionalActivity extends AppCompatActivity {

    private TextView text_name, text_condition;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional);
        init();
    }

    public void btnDone(View view) {//

        Intent intent = new Intent(AdditionalActivity.this, ProcessActivity.class);
        startActivity(intent);
    }
    protected void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("Username");
        text_name = (TextView) findViewById(R.id.additional_name);
        text_name.setText(name +"  !");
       // text_condition = (TextView) findViewById(R.id.text_condition);
       // text_condition.setText("在线");
    }

}
