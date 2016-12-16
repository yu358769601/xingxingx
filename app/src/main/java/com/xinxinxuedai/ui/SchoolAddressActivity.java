package com.xinxinxuedai.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.SchoolAddressActivity.SchoolAddressActivity_C;
import com.xinxinxuedai.MVP.SchoolAddressActivity.SchoolAddressActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogCallBack;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogSelect;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopTextView;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.view.initAction_Bar;

import java.util.ArrayList;


//学校信息activity
public class SchoolAddressActivity extends BaseActivity implements View.OnClickListener, SchoolAddressActivity_C {

    private EditText schooladdress_tv;
    private TextView schooladdress_et_1;
    private EditText schooladdress_et_2;
    private TextView schooladdress_tv_sub;
    private TextView schooladdress_tv2;
    private RelativeLayout schooladdress_rl;
    private initAction_Bar relativeLayout_title;
    private InnerReceiver mReceiver;
    private SchoolAddressActivity_P mP;
    private String[] mCityDatas = {"","","","","","","","",""};

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
        //学校名字
        schooladdress_tv = (EditText) findViewById(R.id.schooladdress_tv);
        schooladdress_tv.setOnClickListener(this);
        //入学年份
        schooladdress_et_1 = (TextView) findViewById(R.id.schooladdress_et_1);
        schooladdress_et_1.setOnClickListener(this);
        //门牌号
        schooladdress_et_2 = (EditText) findViewById(R.id.schooladdress_et_2);
        schooladdress_et_2.setOnClickListener(this);
        //学校所在区域
        schooladdress_tv2 = (TextView) findViewById(R.id.schooladdress_tv2);
        schooladdress_tv2.setOnClickListener(this);
        //提交
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
                textView.setText("学校信息");
            }
        });


        mP.getCallBackData();
    }

    /**
     * 回显结果返回给activity
     *
     * @param getInfo
     */
    @Override
    public void setCallBackData(GetInfo getInfo) {
        LogUtils.i("这里有问题 关于地址的问题"+getInfo);
        //学校名字
        if (!TextUtils.isEmpty(getInfo.loan_school_name))
            schooladdress_tv.setText(getInfo.loan_school_name);
        //入学年份
        if (!TextUtils.isEmpty(getInfo.loan_admission_school))
            schooladdress_et_1.setText(getInfo.loan_admission_school);
        //学校所在省市
        if (!TextUtils.isEmpty(getInfo.loan_province)||!TextUtils.isEmpty(getInfo.loan_city)){
            if (getInfo.loan_province.equals(getInfo.loan_city)){

                schooladdress_tv2.setText(getInfo.loan_province+"市");
                mCityDatas[0] = getInfo.loan_province;
                mCityDatas[1] = getInfo.loan_province;
            }else{
                schooladdress_tv2.setText(getInfo.loan_province+"省"+getInfo.loan_city+"市");
                mCityDatas[0] = getInfo.loan_province;
                mCityDatas[1] = getInfo.loan_city;
            }
        }
        if (!TextUtils.isEmpty(getInfo.loan_present_address))
            schooladdress_et_2.setText(getInfo.loan_present_address);
    }

    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {
        finish();
    }

    /**
     * 清除的方法
     */
    @Override
    public void dump() {
          schooladdress_tv = null;
          schooladdress_et_1= null;
          schooladdress_et_2= null;
          schooladdress_tv_sub= null;
          schooladdress_tv2= null;
          schooladdress_rl= null;
          relativeLayout_title= null;
          mReceiver= null;
          mP= null;
          mCityDatas = null;
    }


    //接收别的地方过来的数据 写一个内容类
    public class InnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //使用intent获取发送过来的数据
            Bundle extras = intent.getExtras();
            mCityDatas = extras.getStringArray("cityData");
            LogUtils.i("过来的数据是"+ mCityDatas);
            if (null!= mCityDatas){
                if ("重庆".equals(mCityDatas[1])||"上海".equals(mCityDatas[1])||"北京".equals(mCityDatas[1])||"天津".equals(mCityDatas[1])){

                    schooladdress_tv2.setText(mCityDatas[1]+"市");
                }else {

                    schooladdress_tv2.setText(mCityDatas[0]+"省"+ mCityDatas[1]+"市");
                }



            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消广播
        unregisterReceiver(mReceiver);
        dump();
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
              //学校所在区域
              case R.id.schooladdress_tv2:
                  intent.setClass(this,SelectCityActivity.class);
                  startActivity(intent);
              break;
              //入学年份
              case R.id.schooladdress_et_1:
                  final ArrayList<String> strings1 = new ArrayList<>();

                  for (int i = 2016; i > 2008; i--) {
                      strings1.add(i+"年");
                  }
                  UtilsDialog.showDialogRadioGroup(this, "选择入学年份", strings1, new UtilsDialogCallBack() {
                      @Override
                      public void RadioGroupNum(int selectNum, String selectNumInfo) {
                          schooladdress_et_1.setText(selectNumInfo);
                      }
                  }, new UtilsDialogSelect() {
                      @Override
                      public void selectCallBack(int selectNum) {
                         LogUtils.i("选择的是"+strings1.get(selectNum));
                      }
                  });
                  break;

              //提交消息
              case R.id.schooladdress_tv_sub:
                  ArrayList<TextView> array = new ArrayList<>();
                  array.add(schooladdress_tv);
                  array.add(schooladdress_et_1);
                  array.add(schooladdress_tv2);
                  array.add(schooladdress_et_2);
                  ArrayList<TextView> views = UtilsLoopTextView.addTagList(array);
                  final ArrayList<String> strings = new ArrayList<>();
                  strings.add("学校名为空");
                  strings.add("入学年份为空");
                  strings.add("学校所在地区未选择");
                  strings.add("门牌号为空");
                  mP.setSub(views,strings,mCityDatas);
                  break;
          }
    }
}
