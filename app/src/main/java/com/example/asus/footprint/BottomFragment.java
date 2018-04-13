package com.example.asus.footprint;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ASUS on 2018/4/3.
 */

public class BottomFragment extends Fragment {

    private ImageButton imgBtn_Process;
    private ImageButton imgBtn_Activity;
    private ImageButton imgBtn_Plan;
    private ImageButton imgBtn_Profile;
    private OnDataTransmissionListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view_bottom = inflater.inflate(R.layout.bottom_bar, container, false);
        imgBtn_Process = view_bottom.findViewById(R.id.imgProcess);
        imgBtn_Activity = view_bottom.findViewById(R.id.imgActivity);
        imgBtn_Plan = view_bottom.findViewById(R.id.imgPlan);
        imgBtn_Profile = view_bottom.findViewById(R.id.imgProfile);

        imgBtn_Process.setOnClickListener(new onClickProcess());
        imgBtn_Activity.setOnClickListener(new onClickActivity());
        imgBtn_Plan.setOnClickListener(new onClickPlan());
        imgBtn_Profile.setOnClickListener(new onClickProfile());
        return view_bottom;
    }

    private class onClickProcess implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //EventBus.getDefault().post("1000");

        }

    }

    private class onClickActivity implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }

    }

    private class onClickPlan implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }

    }

    private class onClickProfile implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }

    }

    //接口回调的方法
    public interface OnDataTransmissionListener {
        public void dataTransmission(String data);
    }

    public void setOnDataTransmissionListener(OnDataTransmissionListener mListener) {
        this.mListener = mListener;
    }

}
