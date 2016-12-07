package com.xinxinxuedai.view.VideoView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;

import mabeijianxi.camera.views.SurfaceVideoView;

/**
 * Created by 35876 于萌萌
 * 创建日期: 13:21 . 2016年12月07日
 * 描述:播放视频的自定义View
 * <p>
 * <p>
 * 备注:
 */

public class MyVideoView extends RelativeLayout {

    private View mView;
    private TextView txt_right;
    private SurfaceVideoView videoview;
    private ImageView play_status;
    private ProgressBar loading;
    private RelativeLayout root;
    private TextView mTxt_right;
    private SurfaceVideoView mVideoview;
    private ProgressBar mLoading;
    private RelativeLayout mRoot;
    private ImageView mPlay_status;

    public MyVideoView(Context context) {
        super(context);
        initView(context);
    }


    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.activity_video_player, this);

        mRoot = (RelativeLayout) mView.findViewById(R.id.root);

        mTxt_right = (TextView) mView.findViewById(R.id.txt_right);
        //主角
        mVideoview = (SurfaceVideoView) mView.findViewById(R.id.videoview);
        //载入圈圈
        mLoading = (ProgressBar) mView.findViewById(R.id.loading);
        //状态
        mPlay_status = (ImageView) mView.findViewById(R.id.play_status);


    }

    public SurfaceVideoView getVideoview(){
        return mVideoview;
    }

    public ProgressBar getLoading(){
        return mLoading;
    }
    public RelativeLayout getRoot(){
        return mRoot;
    }
    public ImageView getPlay_status(){
        return mPlay_status;
    }

}
