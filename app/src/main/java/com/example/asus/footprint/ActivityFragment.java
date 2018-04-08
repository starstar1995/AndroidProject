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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ASUS on 2018/4/3.
 */

public class ActivityFragment extends Fragment {

    private BindService bindService;
    private ProcessActivity mProcessActivity;
    private TextView textView;
    private TextView textView1;
    private EditText editText;
    private int value1;
    private int value2;
    private int value3;
    private boolean isBind;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                textView.setText(msg.arg1 + "");
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_layout, container, false);
        //getActivity().setContentView(R.layout.main_layout);
        textView = view.findViewById(R.id.busu);
        textView1 = view.findViewById(R.id.textView15);
        value1 = Integer.parseInt(textView.getText().toString());
        Intent intent = new Intent(getActivity(), BindService.class);
        isBind =  getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);
        View view1 = inflater.inflate(R.layout.profile_layout, container, false);
        editText = view1.findViewById(R.id.editText9);
        value2 = Integer.parseInt(editText.getText().toString());
        value3 = value2 - value1;
        textView1.setText(value3+" steps to your goal");
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
        if (isBind) {
            getActivity().unbindService(serviceConnection);
        }
    }

}
