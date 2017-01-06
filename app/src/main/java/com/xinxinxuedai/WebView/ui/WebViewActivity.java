package com.xinxinxuedai.WebView.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

public class WebViewActivity extends BaseActivity {

    private initAction_Bar mRelativeLayout_title;
    private WebView mWb;
  //  private String  path = "https://www.baidu.com";
    private String  path = "http://192.168.0.202/qichen/choujiang.php?loan_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_web_view;
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
                textView.setText("抽奖");
            }
        });


        mWb = (WebView) findViewById(R.id.wb);
        initData();
    }

    @Override
    public void initP() {

    }

    @Override
    public void initData() {
        //Share.getToken(AppContext.getApplication());


        mWb.loadUrl(path+Share.getToken(AppContext.getApplication()));


        WebSettings settings = mWb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.i("load开始加载"+System.currentTimeMillis());
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                LogUtils.i("load加载资源" + System.currentTimeMillis());
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogUtils.i("load页面完成"+System.currentTimeMillis());
            }
        });
    }

    @Override
    public void initListener() {

    }



}
