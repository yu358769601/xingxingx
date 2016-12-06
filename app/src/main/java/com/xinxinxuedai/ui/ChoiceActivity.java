package com.xinxinxuedai.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.OtherUtils;
import com.xinxinxuedai.Utils.UtilsBroadcastReceiver;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//选择获取图片的方式activity
public class ChoiceActivity extends BaseActivity implements View.OnClickListener {

    public final static int RESULT_CAPTURE = 3501;//从照相机
    public final static int RESULT_IMAGE = 3502;//从相册
    private static final String TAG = "抉择";

    private String formatpath;//图片储存路径
    private String cameraPath;

    private TextView choice_tv1;
    private TextView choice_tv2;
    private RelativeLayout activity_choice;

    //我是因为什么 而进来的
    private int mClassTag;
    private File mTmpFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBox();
        initImage();
        initView();
    }

    private void getBox() {
        Bundle extras = getIntent().getExtras();
        mClassTag = extras.getInt("classTag");

    }

    private void initImage() {
        String path = Environment.getExternalStorageDirectory().getPath();//获取内存卡路径
        formatpath = String.format("%1$s/Test1/", path);
        verifyDirectory(formatpath);//创建照相后的照片所储存的文件夹

    }
    private Bitmap bm;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CAPTURE && resultCode == RESULT_OK) {//从照相机成功返回
//            Uri parse = Uri.parse(cameraPath);
//            String s = "file://" + cameraPath;
           // Uri parse = Uri.parse(mTmpFile);
            String s = "file://" + mTmpFile;
           // Log.e("parse", parse + "");
            LogUtils.i("返回来的照相机图片路径"+s);
            UtilsBroadcastReceiver.sendBroadcastReceiver(AppContext.getApplication(),"getData",mClassTag+"",s);
            finish();
          //  myuploadFile(s);

        } else if (requestCode == RESULT_IMAGE && resultCode == RESULT_OK) {//从相册成功返回
            Uri originalUri = data.getData();//图片路径
            ContentResolver resolver = getContentResolver();
            Log.e("originalUri", originalUri + "");
            if (originalUri != null) {
                //  myuploadFile(originalUri.getPath());
                // LogUtils.i("返回来的相册路径"+originalUri.getPath());
                try {
                    bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片
                    String[] proj = {MediaStore.Images.Media.DATA};
                    //好像是android多媒体数据库的封装接口，具体的看Android文档
                    Cursor cursor = managedQuery(originalUri, proj, null, null, null);
                    //按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
                    cursor.moveToFirst();
                    //最后根据索引值获取图片路径
                    String path = cursor.getString(column_index);
                    //myuploadFile(path);
                    LogUtils.i("返回来的相册路径"+path);
                    UtilsBroadcastReceiver.sendBroadcastReceiver(AppContext.getApplication(),"getData",mClassTag+"",path);


                    finish();
                }catch (IOException e) {

                    Log.e(TAG,e.toString());

                }

            }
        }
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_choice;
    }
    /**
     * 创建文件夹
     *
     * @param directory
     */
    public static void verifyDirectory(String directory) {
        File file = new File(directory);
        if (file.exists() == true) {
            if (file.isDirectory() == true) {
                return;
            } else {
                file.delete();
            }
        }
        file.mkdirs();
    }
    @Override
    public void initView() {


        choice_tv1 = (TextView) findViewById(R.id.choice_tv1);
        choice_tv1.setOnClickListener(this);
        choice_tv2 = (TextView) findViewById(R.id.choice_tv2);
        choice_tv2.setOnClickListener(this);
        activity_choice = (RelativeLayout) findViewById(R.id.activity_choice);
        activity_choice.setOnClickListener(this);
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
//    camera.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            //创建照片名
//            cameraPath = String.format("%1$s%2$s.jpg", formatpath, getNameByData());
//            intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(cameraPath)));//打开相机,并传过去相片名
//            startActivityForResult(intent1, RESULT_CAPTURE);
//        }
//    });
//    image.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            intent2.setType("image/*");
//            intent2.putExtra("return-data", true);
//            startActivityForResult(intent2, RESULT_IMAGE);
//        }
//    });
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choice_tv1:
                UtilsToast.showToast(AppContext.getApplication(), "点击了拍照");
                camera();
                break;
            case R.id.choice_tv2:
                UtilsToast.showToast(AppContext.getApplication(), "点击了从相册选取");
                image();
                break;

            case R.id.activity_choice:
              //  UtilsToast.showToast(AppContext.getApplication(), "点击了外框");
                finish();
                break;
        }
    }
    /**
     * 返回创建时间
     *
     * @return
     */
    public static String getNameByData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSS", Locale.getDefault());
        return dateFormat.format(new Date());
    }
  //  public final static int REQUEST_CAMERA = 1;
    /**
     * 点了照相
     */
    public void camera(){
//        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            //创建照片名
//            cameraPath = String.format("%1$s%2$s.jpg", formatpath, getNameByData());
//            intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(cameraPath)));//打开相机,并传过去相片名
//            startActivityForResult(intent1, RESULT_CAPTURE);
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(getPackageManager()) != null){
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            mTmpFile = OtherUtils.createFile(getApplicationContext());
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            startActivityForResult(cameraIntent, RESULT_CAPTURE);
        }else{
//            Toast.makeText(getApplicationContext(),
//                    R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 点了相册
     */
    public void image(){
        Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent2.setType("image/*");
            intent2.putExtra("return-data", true);
            startActivityForResult(intent2, RESULT_IMAGE);
    }


}
