package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.NoHttp.controlQueue.ControlQueue;
import com.xinxinxuedai.Utils.NoHttp.queue.Request;
import com.xinxinxuedai.Utils.NoHttp.task.NetworkInterface;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

import static com.xinxinxuedai.Utils.NoHttp.controlQueue.ControlQueue.getControlQueue;

public class Test2Activity extends BaseActivity implements View.OnClickListener {

    private initAction_Bar mRelativeLayout_title;
    private Button test2_bt1;
    private TextView test2_tv;
    private TextView test2_tv_updata;
    private ControlQueue mControlQueue;
    private Button test2_bt2;

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
        test2_bt2 = (Button) findViewById(R.id.test2_bt2);
        test2_bt2.setOnClickListener(this);
    }

    @Override
    public void initP() {
    }

    @Override
    public void initData() {


        Request request = new Request();
        request.setUrl("http://www.baidu.com");
        request.setOrder(0);

        mControlQueue = getControlQueue(5, new NetworkInterface() {

            /**
             * 返回结果
             *
             * @param result
             */
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
                test2_tv_updata.setText(i+"");
            }
        });
        mControlQueue.add(request);
        mControlQueue.start();
    }

    @Override
    public void initListener() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test2_bt1:
                initData();

//                String url = "https://www.baidu.com";
//
//
//                MultiAsynctaskNetwork multiAsynctaskNetwork = new MultiAsynctaskNetwork(url,new NetworkInterface() {
//                    @Override
//                    public void onResult(String result) {
//                        test2_tv.setText(result);
//                    }
//
//                    /**
//                     * 进度
//                     *
//                     * @param i
//                     */
//                    @Override
//                    public void onUpData(int i) {
//                        LogUtils.i("进度是"+i);
//                        test2_tv_updata.setText(i+"");
//                    }
//                });
                break;
            case R.id.test2_bt2:
                if (null != mControlQueue)
                    mControlQueue.stop();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mControlQueue)
            mControlQueue.stop();
    }
}
