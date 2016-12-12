package com.xinxinxuedai.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.SchoolAddressActivity.SchoolAddressActivity_C;
import com.xinxinxuedai.MVP.SchoolAddressActivity.SchoolAddressActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopTextView;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

import java.util.ArrayList;


//选择地区activity
public class SchoolAddressActivity extends BaseActivity implements View.OnClickListener, SchoolAddressActivity_C {

    private TextView schooladdress_tv;
    private EditText schooladdress_et_1;
    private EditText schooladdress_et_2;
    private TextView schooladdress_tv_sub;
    private TextView schooladdress_tv2;
    private RelativeLayout schooladdress_rl;
    private initAction_Bar relativeLayout_title;
    private InnerReceiver mReceiver;
    private SchoolAddressActivity_P mP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        //接收_选中城市_广播
        initRegisterReceiver();
        initView();
    }

    private void initRegisterReceiver() {
        //生命周期方法里面注册广播
        //注册广播接收者
        //"cityInfo","cityData"
        IntentFilter filter = new IntentFilter("cityInfo");
        //在成员变量的位置 创建一个  广播接收类
        mReceiver = new InnerReceiver();
        registerReceiver(mReceiver, filter);

    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_school_address;
    }

    @Override
    public void initView() {

        schooladdress_tv = (TextView) findViewById(R.id.schooladdress_tv);
        schooladdress_tv.setOnClickListener(this);

        schooladdress_et_1 = (EditText) findViewById(R.id.schooladdress_et_1);
        schooladdress_et_1.setOnClickListener(this);

        schooladdress_et_2 = (EditText) findViewById(R.id.schooladdress_et_2);
        schooladdress_et_2.setOnClickListener(this);

        schooladdress_tv2 = (TextView) findViewById(R.id.schooladdress_tv2);
        schooladdress_tv2.setOnClickListener(this);

        schooladdress_tv_sub = (TextView) findViewById(R.id.schooladdress_tv_sub);
        schooladdress_tv_sub.setOnClickListener(this);

        schooladdress_rl = (RelativeLayout) findViewById(R.id.schooladdress_rl);
        schooladdress_rl.setOnClickListener(this);

        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("选择地区");
            }
        });
    }





    //接收别的地方过来的数据 写一个内容类
    public class InnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //使用intent获取发送过来的数据
            Bundle extras = intent.getExtras();
            String[] cityDatas = extras.getStringArray("cityData");
            LogUtils.i("过来的数据是"+cityDatas);
            if (null!=cityDatas){

                schooladdress_tv2.setText(cityDatas[0]+"省"+cityDatas[1]+"市");
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消广播
        unregisterReceiver(mReceiver);
    }



    @Override
    public void initP() {
        mP = SchoolAddressActivity_P.getP(this);
        mP.setCallBack(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        //SelectCityActivity
          switch (v.getId()){
              case R.id.schooladdress_tv2:
                  intent.setClass(this,SelectCityActivity.class);
                  startActivity(intent);
              break;
              case R.id.schooladdress_tv_sub:
                  ArrayList<TextView> array = new ArrayList<>();
                  array.add(schooladdress_tv);
                  array.add(schooladdress_et_1);
                  array.add(schooladdress_tv2);
                  array.add(schooladdress_et_2);
                  ArrayList<TextView> views = UtilsLoopTextView.addTagList(array);
                  final ArrayList<String> strings = new ArrayList<>();
                  strings.add("学校所在地区为空");
                  strings.add("学校名字为空");
                  strings.add("学校所在地区未选择");
                  strings.add("门牌号为空");
                  mP.setSub(views,strings);
                  break;
          }
    }
}
