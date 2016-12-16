package com.xinxinxuedai.ui;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsBroadcastReceiver;
import com.xinxinxuedai.Utils.UtilsMeasure;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.SetLoanStatus;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.SetLoanStatus_Request;
import com.xinxinxuedai.upFile.HttpMultipartPost;
import com.xinxinxuedai.util.Constants;
import com.xinxinxuedai.view.VideoView.MyVideoView;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_1;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import mabeijianxi.camera.MediaRecorderActivity;
import mabeijianxi.camera.VCamera;
import mabeijianxi.camera.model.MediaRecorderConfig;
import mabeijianxi.camera.util.DeviceUtils;
import mabeijianxi.camera.views.SurfaceVideoView;

//上传视频activity
public class UploadVideoActivity extends BaseActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, SurfaceVideoView.OnPlayStateListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnCompletionListener {

    private initAction_Bar relativeLayout_title;
    private TextView uploadvideo_title;
    private XueDaiButton_1 uploadpictures_xd_1;
    private TextView uploadpictures_tv_reset;
    private TextView uploadpictures_tv_sub;
    private RelativeLayout uploadvideo_rl;
    private int displayWidth;
    private int displayHeight;
    private String videoUri;
    private MyVideoView mUploadpictures_mvv;



    private SurfaceVideoView mVideoview;


    /**
     * 是否需要回复播放
     */
    private boolean mNeedResume;

    /**
     * 暂停按钮
     */
    private View mPlayerStatus;
    private View mLoading;
    private ImageView mPlay_status;
    private RelativeLayout mRoot;
    private UrlBroadcastReceiver mUrlBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
        getBox();
        //初始化小视频
        initSmallVideo(this);
        initView();
        initP();
    }

    private void getBox() {
//        Intent intent = getIntent();
//        videoUri = intent.getStringExtra(MediaRecorderActivity.VIDEO_URI);
//        LogUtils.i("视频路径返回来的数据是"+videoUri);

    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_upload_video;
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
                textView.setText("上传视频");
            }
        });


        uploadvideo_title = (TextView) findViewById(R.id.uploadvideo_title);
        uploadvideo_title.setOnClickListener(this);

        uploadpictures_xd_1 = (XueDaiButton_1) findViewById(R.id.uploadpictures_xd_1);
        uploadpictures_xd_1.setOnClickListener(this);

        uploadpictures_tv_reset = (TextView) findViewById(R.id.uploadpictures_tv_reset);
        uploadpictures_tv_reset.setOnClickListener(this);

        uploadpictures_tv_sub = (TextView) findViewById(R.id.uploadpictures_tv_sub);
        uploadpictures_tv_sub.setOnClickListener(this);

        uploadvideo_rl = (RelativeLayout) findViewById(R.id.uploadvideo_rl);
        uploadvideo_rl.setOnClickListener(this);

        //主角
        mUploadpictures_mvv = (MyVideoView) findViewById(R.id.uploadpictures_mvv);


        initData();
    }

    public void  openVideo(){
        //如果是有视频
        if (null!=videoUri){

            LogUtils.i("路径是"+videoUri);
           // RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) uploadpictures_xd_1.getLayoutParams();

            uploadpictures_xd_1.setVisibility(View.INVISIBLE);

            mUploadpictures_mvv.setVisibility(View.VISIBLE);

            int[] measure = UtilsMeasure.measure(uploadpictures_xd_1);
            RelativeLayout.LayoutParams  layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,measure[1] );
            mUploadpictures_mvv.setLayoutParams(layoutParams);

//            //设置跟 按钮一样大
//            mUploadpictures_mvv.setLayoutParams(layoutParams);

            mVideoview = mUploadpictures_mvv.getVideoview();

            mLoading = mUploadpictures_mvv.getLoading();

            mPlay_status = mUploadpictures_mvv.getPlay_status();

            mRoot = mUploadpictures_mvv.getRoot();

            mVideoview.setOnPreparedListener(this);
            mVideoview.setOnPlayStateListener(this);
            mVideoview.setOnErrorListener(this);
            mVideoview.setOnClickListener(this);
            mVideoview.setOnInfoListener(this);
            mVideoview.setOnCompletionListener(this);

            //设置路径
            mVideoview.setVideoPath(videoUri);
            if (mVideoview.isPlaying()) {
                mVideoview.pause();
            }
            uploadpictures_tv_reset.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void initP() {

    }
    boolean b = false;
    @Override
    public void initData() {


        uploadpictures_xd_1.setCallBack(new button_CallBack() {
            @Override
            public void button_Click(View v) {
                if (null==videoUri){
                   //说明没有视频
                    UtilsToast.showToast(AppContext.getApplication(),"正在准备录制视频");
                    //开始录制
                    go();
                }else{
                    //说明有视频
                    //SurfaceVideoView surfaceVideoView = new SurfaceVideoView(AppContext.getApplication());

                }
            }
        })
                .setVisbilityStar(false)
                .setVisbilityInfotext(false)
                .setTopDrawableVisibility(false)
                .setTextColor(getResources().getColor(R.color.home_tv1))
                .setText("请上传视频");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayWidth = displayMetrics.widthPixels;
        displayHeight = displayMetrics.heightPixels;
        // 第一个按钮，宽度100%，高度25%
        RelativeLayout.LayoutParams  mParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                (int) (displayHeight * 0.35f + 0.5f));
        RelativeLayout xuedai_button1 = uploadpictures_xd_1.getXuedai_button1_rl();
        xuedai_button1.setLayoutParams(mParams);
        TextView xuedai_button1_tv = uploadpictures_xd_1.getXuedai_button1_tv();
        xuedai_button1_tv.setGravity(RelativeLayout.CENTER_IN_PARENT);
    }

    @Override
    public void initListener() {

    }

    /**
     * 更新界面信息的广播
     */
    public void initReceiver(){
        mUrlBroadcastReceiver = new UrlBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("VIDEO_URI");
        registerReceiver(mUrlBroadcastReceiver, intentFilter);
    }

    class UrlBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //路径
            videoUri = intent.getStringExtra(MediaRecorderActivity.VIDEO_URI);
            LogUtils.i("回来的数据是"+videoUri);
            FileInputStream soundStream = null;
            try {
                soundStream = new FileInputStream(videoUri);
                //String fileName = videoUri.substring(videoUri.lastIndexOf("/") + 1);
                String fileName = "xingxingdai_loan_video1.mp4";
                LogUtils.i("转换完的语音格式是"+fileName);
                //发送请求 把语音地址发上去
               // String url = "http://192.168.4.102:8080/my/upload";
                String token = Share.getToken(context);
                String loan_area = Share.getString(context, "loan_area");
                String xingxing = "xingxingdai";

                String url = Constants.up_image_url+"?"+"app="+xingxing+"&loan_area="+loan_area+"&loan_id="+token;


                HttpMultipartPost post = new HttpMultipartPost(context, url, soundStream, fileName);
                post.setCallBackMsg(new HttpMultipartPost.CallBackMsg() {
                    @Override
                    public void msg(JSONObject msg) {
                        LogUtils.i("视频"+msg);
                        openVideo();
                    }
                });
                post.execute();
                //post.execute(new HttpMultipartPost.Param("time", String.valueOf(Math.round(mTime))));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mVideoview != null && mNeedResume) {
            mNeedResume = false;
            if (mVideoview.isRelease())
                mVideoview.reOpen();
            else
                mVideoview.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoview != null) {
            if (mVideoview.isPlaying()) {
                mNeedResume = true;
                mVideoview.pause();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mVideoview != null) {
            mVideoview.release();
            mVideoview = null;
        }

        unregisterReceiver(mUrlBroadcastReceiver);
        dump();
        super.onDestroy();
    }

    public  void dump(){
          relativeLayout_title = null;
          uploadvideo_title = null;
          uploadpictures_xd_1 = null;
          uploadpictures_tv_reset = null;
          uploadpictures_tv_sub = null;
          uploadvideo_rl = null;
          mUploadpictures_mvv = null;
    }

    /**
     * 开始录制
     */
    public void go() {
        MediaRecorderConfig config = new MediaRecorderConfig.Buidler()
                .doH264Compress(true)
                .smallVideoWidth(480)
                .smallVideoHeight(360)
                .recordTimeMax(10 * 1000)
                .maxFrameRate(20)
                .minFrameRate(8)
                .captureThumbnailsTime(1)
                .recordTimeMin((int) (1.5 * 1000))
                .build();
        MediaRecorderActivity.goSmallVideoRecorder(this, UploadVideoActivity.class.getName(), config);
    }

    public static void initSmallVideo(Context context) {
        // 设置拍摄视频缓存路径
        File dcim = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (DeviceUtils.isZte()) {
            if (dcim.exists()) {
                VCamera.setVideoCachePath(dcim + "/mabeijianxi/");
            } else {
                VCamera.setVideoCachePath(dcim.getPath().replace("/sdcard/",
                        "/sdcard-ext/")
                        + "/mabeijianxi/");
            }
        } else {
            VCamera.setVideoCachePath(dcim + "/mabeijianxi/");
        }
        // 开启log输出,ffmpeg输出到logcat
        VCamera.setDebugMode(true);
        // 初始化拍摄SDK，必须
        VCamera.initialize(context);
    }



    @Override
    public void onPrepared(MediaPlayer mp) {
        mVideoview.setVolume(SurfaceVideoView.getSystemVolumn(this));
        mVideoview.start();
        // new Handler().postDelayed(new Runnable() {
        //
        // @SuppressWarnings("deprecation")
        // @Override
        // public void run() {
        // if (DeviceUtils.hasJellyBean()) {
        // mVideoView.setBackground(null);
        // } else {
        // mVideoView.setBackgroundDrawable(null);
        // }
        // }
        // }, 300);
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {// 跟随系统音量走
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            case KeyEvent.KEYCODE_VOLUME_UP:
                mVideoview.dispatchKeyEvent(this, event);
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onStateChanged(boolean isPlaying) {
        mPlay_status.setVisibility(isPlaying ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (!isFinishing()) {
            // 播放失败
        }
        finish();
        return false;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.videoview:
            case R.id.root:
                // finish();
                if (mVideoview.isPlaying()){
                    mVideoview.pause();

                }else{
                    mVideoview.start();
                }
                break;
            //点了重新录制
            case R.id.uploadpictures_tv_reset:
                go();
            break;
            //申请借款
            case R.id.uploadpictures_tv_sub:
                SetLoanStatus_Request.request(AppContext.getApplication(), new NetWorkCallBack<SetLoanStatus>() {
                    @Override
                    public void onSucceed(SetLoanStatus setLoanStatus, int dataMode) {
                        finish();
                        //发送_关闭activity_广播
                        UtilsBroadcastReceiver.sendBroadcastReceiver(AppContext.getApplication(),"uploadpictures_","close","close");
                    }

                    @Override
                    public void onError(String jsonObject) {

                    }
                });
                break;


        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (!isFinishing())
            mVideoview.reOpen();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                // 音频和视频数据不正确
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (!isFinishing())
                    mVideoview.pause();
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                if (!isFinishing())
                    mVideoview.start();
                break;
            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                if (DeviceUtils.hasJellyBean()) {
                    mVideoview.setBackground(null);
                } else {
                    mVideoview.setBackgroundDrawable(null);
                }
                break;
        }
        return false;
    }

}
