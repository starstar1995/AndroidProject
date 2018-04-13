package com.example.asus.footprint;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ASUS on 2018/4/3.
 */

public class ProcessFragment extends Fragment {
    private BindService bindService;
    private TextView view_time;
    private int value_step;
    private TextView view_average;
    private int day;
    String str;
    private TextView view_busu;
    private boolean isBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.process_layout, container, false);
        view_average = view.findViewById(R.id.textView2);
        //view_average.setText(read("month.txt"));

        View view1 = inflater.inflate(R.layout.activity_layout, container, false);
        view_busu = view1.findViewById(R.id.busu);
        String str_busu = view_busu.getText().toString();
        value_step = Integer.parseInt(str_busu);


        EventBus.getDefault().register(this);

        time( view);



        return view;
    }
    private void time(View view)
    {
        SimpleDateFormat sd=new SimpleDateFormat("MM");
        String date=sd.format(new java.util.Date());
        String[] date2={"01","02","03","04","05","06","07","08","09","10","11","12"};
        String moth[] = {"JANUARY STATUS","FEBRUARY STATUS","MARCH STATUS","APRIL STATUS","MAY STATUS","JUNE STATUS","JULY STATUS","AUGEST STATUS","SEPTEMBER STATUS","OCTOBER STATUS","NOVEMBER STATUS","DECEMBER STATUS"};


        view_time = view.findViewById(R.id.textView9);



        for ( int i=0;i<12;i++)
        {
            boolean btn = date.equals(date2[i]);
            if (btn==true)
            {  view_time.setText(moth[i]);

            }

        }



     /*   if (index >0)
        {
            view_time.setText(date);
        }
        else { view_time.setText(index);}*/
        /*if (date=="01")
        {
            view_time.setText("JANUARY STATUS");
        }
        else if (date=="02")
        {
            view_time.setText("FEBRUARY STATUS");
        }
        else if (date=="03")
        {
            view_time.setText("MARCH STATUS");
        }
        else if (date=="04")
        {
            view_time.setText("APRIL STATUS");
        }
        else if (date=="05")
        {
            view_time.setText("MAY STATUS");
        }
        else if (date=="06")
        {
            view_time.setText("JUNE STATUS");
        }
        else if (date=="07")
        {
            view_time.setText("JULY STATUS");
        }
        else if (date=="08")
        {
            view_time.setText("AUGEST STATUS");
        }
        else if (date=="09")
        {
            view_time.setText("SEPTEMBER STATUS");
        }
        else if (date=="10")
        {
            view_time.setText("OCTOBER STATUS");
        }
        else if (date=="11")
        {
            view_time.setText("NOVEMBER STATUS",DECEMBER STATUS");
        }
        else if (date=="12")
        {
            view_time.setText("DECEMBER STATUS");
        }*/
    }
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
   /* private void average()
    {
        int step[]={0};
        step = new int[31];




    }*/



    private int average(int n,int a)
    {
        return (int)(a/n);
    }

    public void write(String content,String str) {
        FileOutputStream fos = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 指定要写入的文件 和 模式，文件在data/data/包名目录中
            fos = getActivity().openFileOutput(str, Context.MODE_PRIVATE);
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
        TextView view = null;
        StringBuilder sb = new StringBuilder();
        try {
            // 指定要读取的文件
            bufferedReader = new BufferedReader(new InputStreamReader(getActivity().openFileInput(str)));
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

    @Subscribe
    public void onEvent(String data) {
        view_average.setText(data);
    }
    public void setData(String string) {
        view_average.setText(string);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
