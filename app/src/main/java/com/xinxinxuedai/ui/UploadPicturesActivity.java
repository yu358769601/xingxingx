package com.xinxinxuedai.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.UploadPicturesActivity.UploadPicturesActivity_C;
import com.xinxinxuedai.MVP.UploadPicturesActivity.UploadPicturesActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsSetSize;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_1;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

//上传图片activity
public class UploadPicturesActivity extends BaseActivity implements View.OnClickListener, UploadPicturesActivity_C {

    private TextView uploadpictures_tt_title;
    private TextView uploadpictures_tt_title_big;
    private XueDaiButton_1 uploadpictures_xd_1;
    private XueDaiButton_1 uploadpictures_xd_2;
    private XueDaiButton_1 uploadpictures_xd_3;
    private TextView uploadpictures_tv_sub;
    private RelativeLayout uploadpictures_rl;
    private initAction_Bar relativeLayout_title;
    private UploadPicturesActivity_P mUploadPicturesActivity_p;
    boolean tag1 = false;
    boolean tag2 = false;
    boolean tag3 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
        init();
    }

    private void init() {
        //注册广播接收者 接收 选择完了图片返回来的字符串
        //接收_图片路径_广播
        IntentFilter filter = new IntentFilter("getData");
        registerReceiver(receiver, filter);

    }

    //在成员变量的位置 创建一个  广播接收类 接收 选择完了图片返回来的字符串
    private InnerReceiver receiver = new InnerReceiver();

    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {

    }
    /**
     * 清除的方法
     */
    @Override
    public void dump() {
          uploadpictures_tt_title =null;
          uploadpictures_tt_title_big =null;
          uploadpictures_xd_1 =null;
          uploadpictures_xd_2 =null;
          uploadpictures_xd_3 =null;
          uploadpictures_tv_sub =null;
          uploadpictures_rl =null;
          relativeLayout_title =null;
          mUploadPicturesActivity_p =null;
    }

    //接收别的地方过来的数据 写一个内容类
    public class InnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //使用intent获取发送过来的数据
            String msg1 = intent.getStringExtra("1");
            LogUtils.i("过来的数据是1"+msg1);
            if (null!=msg1){

                uploadpictures_xd_1
                        .setImage(msg1)
                        .setRelativeLayout_Pading(0,0,0,0);
                tag1 = true;
                return;
            }

            String msg2 = intent.getStringExtra("2");
            LogUtils.i("过来的数据是2"+msg2);
            if (null!=msg2){
                uploadpictures_xd_2
                        .setImage(msg2)
                        .setRelativeLayout_Pading(0,0,0,0);
                tag2 = true;
                return;
            }

            String msg3 = intent.getStringExtra("3");
            LogUtils.i("过来的数据是3"+msg3);
            if (null!=msg3){
                uploadpictures_xd_3
                        .setImage(msg3)
                        .setRelativeLayout_Pading(0,0,0,0);
                tag3 = true;
                return;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        dump();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_upload_pictures;
    }

    @Override
    public void initView() {
        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("上传图片");
            }
        });
        uploadpictures_tt_title = (TextView) findViewById(R.id.uploadpictures_tt_title);
        uploadpictures_tt_title.setOnClickListener(this);

        uploadpictures_tt_title_big = (TextView) findViewById(R.id.uploadpictures_tt_title_big);
        uploadpictures_tt_title_big.setOnClickListener(this);
        //上传正面身份证
        uploadpictures_xd_1 = (XueDaiButton_1) findViewById(R.id.uploadpictures_xd_1);
        uploadpictures_xd_1.setOnClickListener(this);
        //上传背面身份证
        uploadpictures_xd_2 = (XueDaiButton_1) findViewById(R.id.uploadpictures_xd_2);
        uploadpictures_xd_2.setOnClickListener(this);
        //手持身份证
        uploadpictures_xd_3 = (XueDaiButton_1) findViewById(R.id.uploadpictures_xd_3);
        uploadpictures_xd_3.setOnClickListener(this);
        //提交按钮
        uploadpictures_tv_sub = (TextView) findViewById(R.id.uploadpictures_tv_sub);
        uploadpictures_tv_sub.setOnClickListener(this);

        uploadpictures_rl = (RelativeLayout) findViewById(R.id.uploadpictures_rl);
        uploadpictures_rl.setOnClickListener(this);

        initData();
        }

    @Override
    public void initP() {
        mUploadPicturesActivity_p = UploadPicturesActivity_P.getmRegisterActivity_p(AppContext.getApplication());
        mUploadPicturesActivity_p.setCallBack(this);
        mUploadPicturesActivity_p.getCallBackData();
    }

    @Override
    public void initData() {
        uploadpictures_xd_1
                .setCallBack(new button_CallBack() {
                    @Override
                    public void button_Click(View v) {

                        uploadpictures_xd_1.setTag(1);
                        mUploadPicturesActivity_p.xuedai_buttonClick(uploadpictures_xd_1);
                    }
                })
                .setVisbilityInfotext(false)
                .setVisbilityStar(false)
                .setText("上传正面身份证")
                .setTextColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setRelativeLayout_Pading(0,UtilsSetSize.setPx(this, R.dimen.size_base480_50dp),0,UtilsSetSize.setPx(this, R.dimen.size_base480_50dp));
        uploadpictures_xd_2
                .setCallBack(new button_CallBack() {
                    @Override
                    public void button_Click(View v) {

                        uploadpictures_xd_2.setTag(2);
                        mUploadPicturesActivity_p.xuedai_buttonClick(uploadpictures_xd_2);
                    }
                })
                .setVisbilityInfotext(false)
                .setVisbilityStar(false)
                .setText("上传背面身份证")
                .setTextColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setRelativeLayout_Pading(0,UtilsSetSize.setPx(this, R.dimen.size_base480_50dp),0,UtilsSetSize.setPx(this, R.dimen.size_base480_50dp));

        uploadpictures_xd_3
                .setCallBack(new button_CallBack() {
                    @Override
                    public void button_Click(View v) {

                        uploadpictures_xd_3.setTag(3);
                        mUploadPicturesActivity_p.xuedai_buttonClick(uploadpictures_xd_3);
                    }
                })
                .setVisbilityInfotext(false)
                .setVisbilityStar(false)
                .setText("手持身份证")
                .setTextColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setRelativeLayout_Pading(0,UtilsSetSize.setPx(this, R.dimen.size_base480_50dp),0,UtilsSetSize.setPx(this, R.dimen.size_base480_50dp));

    }




    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.uploadpictures_tv_sub:
                if (!tag1||!tag2||!tag3){
                    UtilsToast.showToast(AppContext.getApplication(),"有图片选择项没有上传");
                    return;
                }
                //打开上传视频
                intent.setClass(AppContext.getApplication(),UploadVideoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
