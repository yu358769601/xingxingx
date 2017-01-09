package com.xinxinxuedai.ui;

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

import static com.xinxinxuedai.util.Constants._url;

/**
 * 服务 和协议
 */
public class Fuwu_and_xieyi_Activity extends BaseActivity {
    private initAction_Bar mRelativeLayout_title;
    private WebView mWb;
    private String  path;
    //  private String  path = "https://www.baidu.com";
    //1协议
    private String  path_1 = _url+"jiekuan.html";
    //0服务
    private String  path_0 = _url+"fuwu.html";
    private int mClassTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBox();
        initView();
    }

    private void getBox() {
        Bundle extras = getIntent().getExtras();
        if (null==extras){
            return;
        }
        mClassTag = extras.getInt("classTag", 0);
        if (mClassTag == 1){
            path = path_1;
        }else{
            path = path_0;
        }

    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_fuwu;
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
                if (mClassTag==1){
                    textView.setText("协议");
                }else{
                    textView.setText("服务");
                }
            }
        });


        mWb = (WebView) findViewById(R.id.wb_fuwu);
        initData();
    }

    @Override
    public void initP() {

    }

    @Override
    public void initData() {
        if (!Share.checkLogin(AppContext.getApplication())){
            finish();
            return;
        }


        String newPath = path;
        LogUtils.i("网站"+newPath+"人员编号"+Share.getToken(AppContext.getApplication()));
        mWb.loadUrl(newPath);
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
