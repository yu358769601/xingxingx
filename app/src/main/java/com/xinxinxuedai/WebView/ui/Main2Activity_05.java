//package com.xinxinxuedai.WebView.ui;
//
//import android.app.Activity;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.xinxinxuedai.R;
//import com.xinxinxuedai.Utils.LogUtils;
//import com.xinxinxuedai.base.BaseActivity;
//import com.xinxinxuedai.view.initAction_Bar;
//
//
//public class Main2Activity_05 extends BaseActivity {
//
//    private WebView mWb;
//    private String  path = "https://www.baidu.com";
//    private initAction_Bar mBase_action_bar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2_05);
//        init();
//    }
//
//    @Override
//    public int getlayouXML() {
//        return 0;
//    }
//
//    @Override
//    public void initView() {
//
//    }
//
//    @Override
//    public void initP() {
//
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    @Override
//    public void initListener() {
//
//    }
//
//    private void init() {
//        mBase_action_bar = (initAction_Bar) findViewById(R.id.base_action_bar);
//        mBase_action_bar
//                .setMode(initAction_Bar.AUTO_ONCLICK_MODE)
//                .setCallBack(new initAction_Bar.Action_bar_call_back() {
//                    @Override
//                    public void getAction_barView_backbutton(TextView textView) {
//                        textView.setText("返回");
//                    }
//
//                    @Override
//                    public void getAction_barView_backbutton_icon(ImageView imageView) {
//
//                    }
//
//                    @Override
//                    public void getAction_barView_title(TextView textView) {
//                        textView.setText("浏览器");
//                    }
//
//                    @Override
//                    public void getAction_barView_right_icon(ImageView imageView) {
//
//                    }
//                });
//        mWb = (WebView) findViewById(R.id.wb);
//        mWb.loadUrl(path);
//        WebSettings settings = mWb.getSettings();
//        settings.setJavaScriptEnabled(true);
//        mWb.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                LogUtils.i("load开始加载"+System.currentTimeMillis());
//            }
//
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                super.onLoadResource(view, url);
//                LogUtils.i("load加载资源" + System.currentTimeMillis());
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                LogUtils.i("load页面完成"+System.currentTimeMillis());
//            }
//        });
//    }
//}
