package com.example.asus.footprint;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;


public class ProcessActivity extends AppCompatActivity implements View.OnClickListener{

    private ProcessFragment mProcess;
    private ActivityFragment mActivity;
    private PlanFragment mPlan;
    private ProfileFragment mProfile;

    private LinearLayout mTabBtnProcess;
    private LinearLayout mTabBtnActivity;
    private LinearLayout mTabBtnPlan;
    private LinearLayout mTabBtnProfile;
    private TextView view_time;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
       // monthstats();



        initViews();
        //time(view_time);
        fragmentManager = getFragmentManager();
        setTabSelection(1);



    }


/*  private void monthstats()
  {
      SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm E");

      String date=sd.format(new java.util.Date());
String str = "111";
      ((TextView) findViewById(R.id.textView9)).setText(str);
      view_time.setText("1111");
     view_time = (TextView) findViewById(R.id.textView9);
  }
*/
    private void initViews()
    {

        //mTabBtnProcess = (LinearLayout) findViewById(R.id.TabBtnProcess);
        mTabBtnActivity = (LinearLayout) findViewById(R.id.TabBtnActivity);
        mTabBtnPlan = (LinearLayout) findViewById(R.id.TabBtnPlan);
        mTabBtnProfile = (LinearLayout) findViewById(R.id.TabBtnProfile);

        //mTabBtnProcess.setOnClickListener(this);
        mTabBtnActivity.setOnClickListener(this);
        mTabBtnPlan.setOnClickListener(this);
        mTabBtnProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //case R.id.TabBtnProcess:
                //setTabSelection(0);
                //break;
            case R.id.TabBtnActivity:
                setTabSelection(1);

                break;
            case R.id.TabBtnPlan:
                setTabSelection(2);
                break;
            case R.id.TabBtnProfile:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }


    private void setTabSelection(int index)
    {
        // 重置按钮
        resetBtn();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            /*case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnProcess.findViewById(R.id.imgProcess)).setImageResource(R.drawable.icons_process_click);
                if (mProcess == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mProcess = new ProcessFragment();
                    transaction.add(R.id.id_content, mProcess);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mProcess);
                }
                break;*/
            case 1:
                ((ImageButton) mTabBtnActivity.findViewById(R.id.imgActivity)).setImageResource(R.drawable.icons_activity_click);
                if (mActivity == null) {
                    mActivity = new ActivityFragment();
                    transaction.add(R.id.id_content, mActivity);
                } else {
                    transaction.show(mActivity);
                }
                break;
            case 2:
                ((ImageButton) mTabBtnPlan.findViewById(R.id.imgPlan)).setImageResource(R.drawable.icons_plan_click);
                if (mPlan == null) {
                    mPlan = new PlanFragment();

                    transaction.add(R.id.id_content, mPlan);
                } else {
                    transaction.show(mPlan);
                }
                break;
            case 3:
                ((ImageButton) mTabBtnProfile.findViewById(R.id.imgProfile)).setImageResource(R.drawable.icons_profile_click);
                if (mProfile == null) {
                    mProfile = new ProfileFragment();
                    transaction.add(R.id.id_content, mProfile);
                } else {
                    transaction.show(mProfile);
                }
                break;
        }transaction.commit();
    }
    private void resetBtn()
    {
        //((ImageButton) mTabBtnProcess.findViewById(R.id.imgProcess))
          //      .setImageResource(R.drawable.icons_process);
        ((ImageButton) mTabBtnActivity.findViewById(R.id.imgActivity))
                .setImageResource(R.drawable.icons_activity);
        ((ImageButton) mTabBtnPlan.findViewById(R.id.imgPlan))
                .setImageResource(R.drawable.icons_plan);
        ((ImageButton) mTabBtnProfile.findViewById(R.id.imgProfile))
                .setImageResource(R.drawable.icons_profile);
    }

    private void hideFragments(FragmentTransaction transaction)
    {
        /*if (mProcess != null)
        {
            transaction.hide(mProcess);
        }*/
        if (mActivity != null)
        {
            transaction.hide(mActivity);
        }
        if (mPlan != null)
        {
            transaction.hide(mPlan);
        }
        if (mProfile != null)
        {
            transaction.hide(mProfile);
        }
    }

}
