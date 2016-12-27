package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.SelectCityActivity.SelectCityActivity_C;
import com.xinxinxuedai.MVP.SelectCityActivity.SelectCityActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsBroadcastReceiver;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

//选择城市activity  二维数组的那个
public class SelectCityActivity extends BaseActivity implements View.OnClickListener, SelectCityActivity_C {

    private RadioGroup selectcity_rg;
    private RelativeLayout selectcity_rl;
    private initAction_Bar relativeLayout_title;
    private SelectCityActivity_P mSelectCityActivity_p;
    private RadioGroup selectcity_rg1;
    private RadioGroup selectcity_rg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_select_city;
    }

    @Override
    public void initView() {

        selectcity_rg1 = (RadioGroup) findViewById(R.id.selectcity_rg1);
        selectcity_rg2 = (RadioGroup) findViewById(R.id.selectcity_rg2);
        //selectcity_rg.setOnClickListener(this);

        selectcity_rl = (RelativeLayout) findViewById(R.id.selectcity_rl);
        selectcity_rl.setOnClickListener(this);

        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("选择城市");
            }
        });

        initData();
    }

    @Override
    public void initP() {
        mSelectCityActivity_p =new  SelectCityActivity_P(AppContext.getApplication());
        mSelectCityActivity_p.setCallBack(this);
    }

    @Override
    public void initData() {
        mSelectCityActivity_p.setRadioGroup(selectcity_rg1,selectcity_rg2);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getData(String sheng, String shi) {
        //发送_选中城市_广播
        UtilsBroadcastReceiver.sendBroadcastReceiver(AppContext.getApplication(),"cityInfo","cityData",new String[]{sheng,shi});
        finish();
    }

    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dump();
    }

    /**
     * 清除的方法
     */
    @Override
    public void dump() {
          selectcity_rg =null;
          selectcity_rl=null;
          relativeLayout_title=null;
          mSelectCityActivity_p=null;
          selectcity_rg1=null;
          selectcity_rg2=null;
    }
}
