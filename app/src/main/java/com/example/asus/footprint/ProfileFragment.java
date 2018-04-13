package com.example.asus.footprint;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ASUS on 2018/4/3.
 */

public class ProfileFragment extends Fragment {

    private EditText edittext;
    private TextView textview;
    private TextView textview1;
    private Button btn;

    private OnDataTransmissionListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.profile_layout, container, false);
        edittext = view.findViewById(R.id.editText9);
        edittext.setText(read("goal.txt"));
        btn = view.findViewById(R.id.button5);

        View view1 =  inflater.inflate(R.layout.activity_layout, container, false);
        textview = view1.findViewById(R.id.busu);
        textview1 = view1.findViewById(R.id.textView15);

        btn.setOnClickListener(new MyOnClickListener());
        return view;
    }

    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //获取editText控件的数据
            String my_string = edittext.getText().toString();
            write(my_string,"goal.txt");
            int stepToGoal = Integer.parseInt(my_string) - Integer.parseInt(textview.getText().toString());
            String str1 = stepToGoal+" steps to your goal";
            //EventBus.getDefault().post(str1);
            Toast.makeText(getActivity(), "数据为:"+my_string+"，已保存", Toast.LENGTH_SHORT).show();

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

    //接口回调的方法
    public interface OnDataTransmissionListener {
        public void dataTransmission(String data);
    }

    public void setOnDataTransmissionListener(OnDataTransmissionListener mListener) {
        this.mListener = mListener;
    }

}
