package com.example.asus.footprint;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

public class AdditionalActivity extends AppCompatActivity {

    private TextView text_name, text_time,text_status;
    private String name,time;

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

        if (name ==null)
        {        text_name = (TextView) findViewById(R.id.additional_name);
                 text_name.setText("Visitor!");

                 write("Visitor","name.txt");

            text_status = (TextView) findViewById(R.id.text_status);
            text_status.setText(" Not logged in");
        }
        else {
            text_name = (TextView) findViewById(R.id.additional_name);
            text_name.setText(name + "  !");

            write(name+"","name.txt");
            text_status = (TextView) findViewById(R.id.text_status);
            text_status.setText(" Logged in");
        }

        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time=sd.format(new java.util.Date());
        text_time = (TextView) findViewById(R.id.text_time);
        text_time.setText(time);

       // text_condition = (TextView) findViewById(R.id.text_condition);
       // text_condition.setText("在线");
    }

    public void write(String content,String str) {
        FileOutputStream fos = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 指定要写入的文件 和 模式，文件在data/data/包名目录中
            fos = openFileOutput(str, Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            //通过bufferedWriter 写入数据
            bufferedWriter.write(content);
            bufferedWriter.flush();
            //Toast.makeText(getActivity(), "写入完成", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public String read(String str) {
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            // 指定要读取的文件
            bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(str)));
            //bufferedReader 读取数据
            String content = bufferedReader.readLine();
            while (content != null) {
                // 当 content有数据的时候，循环读取，直至读完，读取的数据放入StringBuilder中
                sb.append(content);
                content = bufferedReader.readLine();
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


}
