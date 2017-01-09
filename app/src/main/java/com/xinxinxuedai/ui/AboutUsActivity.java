package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loveplusplus.update.AppUtils;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

//关于我们activity
public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    private initAction_Bar mRelativeLayout_title;
    private TextView about_us_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_about_us;
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
                textView.setText("关于我们");
            }
        });


        about_us_version = (TextView) findViewById(R.id.about_us_version);
        about_us_version.setOnClickListener(this);
        initData();
    }

    @Override
    public void initP() {

    }

    @Override
    public void initData() {
        String versionName = AppUtils.getVersionName(AppContext.getApplication());
        UtilsMyText.setHintAddText(about_us_version,versionName);
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

    }
}
