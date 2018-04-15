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
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import android.widget.AdapterView;

/**
 * Created by ASUS on 2018/4/3.
 */

public class ActivityFragment extends Fragment {

    private BindService bindService;
    private TextView textView;
    private TextView textView1;
    private EditText editText;
    private TextView textView2;
    private int value1;
    private int value2;
    private int value3;
    public int value4;
    int day;
    private boolean isBind;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                textView.setText(msg.arg1 + "");
                if(msg.arg2>=0){
                    textView1.setText(msg.arg2+" steps to your goal.");
                }
                else
                {
                    textView1.setText("You have achieved your goal already!");
                }
                //EventBus.getDefault().post(msg.arg1+"");
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_layout, container, false);
        //getActivity().setContentView(R.layout.main_layout);
        textView = view.findViewById(R.id.busu);
        //read(textView);
        value4 = Integer.parseInt(textView.getText().toString());
        textView1 = view.findViewById(R.id.textView15);
        //EventBus.getDefault().register(this);
        value1 = Integer.parseInt(textView.getText().toString());
        Intent intent = new Intent(getActivity(), BindService.class);
        isBind =  getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);

        View view1 = inflater.inflate(R.layout.profile_layout, container, false);
        editText = view1.findViewById(R.id.editText9);
        value2 = Integer.parseInt(editText.getText().toString());
        value3 = value2 - value1;
        textView1.setText(value3+" steps to your goal");
        write(value3+"","goal.txt");
        //String s = String.valueOf(value3);
        //write("0");

        View view2 = inflater.inflate(R.layout.process_layout, container, false);
        textView2 = view2.findViewById(R.id.textView2);

        SimpleDateFormat sd=new SimpleDateFormat("dd");
        String date=sd.format(new java.util.Date());
        day = Integer.parseInt(date);

        return view;
    }

    //和绷定服务数据交换的桥梁，可以通过IBinder service获取服务的实例来调用服务的方法或者数据
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindService.LcBinder lcBinder = (BindService.LcBinder) service;
            bindService = lcBinder.getService();
            bindService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    //当前接收到stepCount数据，就是最新的步数
                    Message message = Message.obtain();
                    message.what = 1;
                    message.arg1 = stepCount;
                    message.arg2 = Integer.parseInt(read("goal.txt"))-stepCount;
                    handler.sendMessage(message);
                    Log.i("MainActivity—updateUi","当前步数"+stepCount);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {  //app被关闭之前，service先解除绑定
        super.onDestroy();
        //EventBus.getDefault().post(textView.getText().toString());
      //  EventBus.getDefault().unregister(this);
        if (isBind) {
            getActivity().unbindService(serviceConnection);
        }
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

}

