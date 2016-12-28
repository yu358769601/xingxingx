package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.NoHttp.task.MultiAsynctaskNetwork;
import com.xinxinxuedai.Utils.NoHttp.task.NetworkInterface;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

public class Test2Activity extends BaseActivity implements View.OnClickListener {

    private initAction_Bar mRelativeLayout_title;
    private Button test2_bt1;
    private TextView test2_tv;
    private TextView test2_tv_updata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_test2;
    }

    @Override
    public void initView() {
        mRelativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        mRelativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("测试2");
            }
        });


        test2_bt1 = (Button) findViewById(R.id.test2_bt1);
        test2_bt1.setOnClickListener(this);
        test2_tv = (TextView) findViewById(R.id.test2_tv);
        test2_tv.setOnClickListener(this);
        test2_tv_updata = (TextView) findViewById(R.id.test2_tv_updata);
        test2_tv_updata.setOnClickListener(this);
    }

    @Override
    public void initP() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test2_bt1:
                String url = "https://www.baidu.com";


                MultiAsynctaskNetwork multiAsynctaskNetwork = new MultiAsynctaskNetwork(url,new NetworkInterface() {
                    @Override
                    public void onResult(String result) {
                        test2_tv.setText(result);
                    }

                    /**
                     * 进度
                     *
                     * @param i
                     */
                    @Override
                    public void onUpData(int i) {
                        LogUtils.i("进度是"+i);
                        test2_tv_updata.setText(i+"");
                    }
                });
                break;
        }
    }
}
