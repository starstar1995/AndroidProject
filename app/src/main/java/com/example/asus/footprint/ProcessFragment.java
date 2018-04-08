package com.example.asus.footprint;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Created by ASUS on 2018/4/3.
 */

public class ProcessFragment extends Fragment {
   // private TextView view_time;
    private int value_step;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.process_layout, container, false);
        //view_time.setText("1111");

        //time( view);
       // average();


        return view;
    }
   /* private void time(View view)
    {
        SimpleDateFormat sd=new SimpleDateFormat("MM");
        String date=sd.format(new java.util.Date());

        view_time = view.findViewById(R.id.textView9);

        if (date=="01")
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
            view_time.setText("NOVEMBER STATUS");
        }
        else if (date=="12")
        {
            view_time.setText("DECEMBER STATUS");
        }
    }*/
   /* private void average()
    {
        int step[]={0};
        step = new int[31];




    }*/

}
