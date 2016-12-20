package com.xinxinxuedai.Utils.UtilsDialog;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.view.dialog.CustomDialog;
import com.xinxinxuedai.view.dialog.DialogCallBack;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 20:22 . 2016年12月04日
 * 描述:零用金的 dialog
 * <p>
 * <p>
 * 备注: dialog 必须 穿 activity  this 要不然不显示  必须在 activity 类里面使用这个工具类
 */

public class UtilsDialog {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static CustomDialog showDialogRadioGroup(final Context context, String setTitle, final List<String> string, final UtilsDialogCallBack callBack , final UtilsDialogSelect utilsDialogSelect){

        Activity activity = (Activity) context;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        TextView textView = new TextView(context);
        textView.setText(setTitle);
        textView.setTextColor(context.getResources().getColor(R.color.black));
        builder.setTitle(setTitle); //设置标题
//        TextView textViewfu = new TextView(context);
//        textViewfu.setText(setMessage);
//        textViewfu.setTextColor(context.getResources().getColor(R.color.black));
//        String s = textViewfu.getText().toString();
//        builder.setMessage(s); //设置内容


        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
       // android:background="@drawable/shape_white"
        relativeLayout.setBackgroundResource(R.drawable.shape_white);
        //relativeLayout.setBackground(context.getDrawable(R.drawable.shape_white));
        relativeLayout.setLayoutParams(layoutParams);

        View inflate = View.inflate(context, R.layout.dialog_radiogroup, relativeLayout);
        final RelativeLayout rl = (RelativeLayout) inflate.findViewById(R.id.rl);
        final RadioGroup radioGroup = new RadioGroup(context);
        //外衣
        RadioGroup.LayoutParams layoutParams1 = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        radioGroup.setLayoutParams(layoutParams1);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i <string.size() ; i++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setLayoutParams(params);
            radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            radioButton.setPadding(20,10,20,10);
            radioButton.setGravity(Gravity.CENTER_VERTICAL);
            radioButton.setText(string.get(i));
            radioButton.setTextColor(context.getResources().getColor(R.color.black));


            Drawable nav_up=context.getResources().getDrawable(R.drawable.my_radiobutton_selector);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            radioButton.setCompoundDrawables(null, null, nav_up, null);

            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    if (radioGroup.getChildAt(i).getId()==checkedId){
                        LogUtils.i("我点了第几个"+i);
                        utilsDialogSelect.selectCallBack(i);
                    }
                }
            }
        });
        rl.addView(radioGroup);

        //设置中间布局
        builder.setContentView(inflate);



        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //LogUtils.i("集合长度"+string.size()+"集合内容"+string+"选择号码 是"+which);

                //如果没有被选中的条目
                if (-1 == radioGroup.getCheckedRadioButtonId()){
                    UtilsToast.showToast(context,"未选择任何条目");
                }else{
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        RadioButton childAt = (RadioButton) radioGroup.getChildAt(i);
                        if (childAt.isChecked()){
                            LogUtils.i("选中的是"+i+"内容是"+string.get(i));
                            callBack.RadioGroupNum(i,string.get(i));
                            dialog.dismiss();
                            return;
                        }
                    }
                   //LogUtils.i("被选中的是"+which+"号"+"内容是"+string.get(which));

                }


                //设置你的操作事项
                //点的确定
               // listener.confirm(selectorButton,CONFIRM);
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
       // builder.create().show();
        //设置窗口的大小
        CustomDialog alertDialog = builder.create();
            alertDialog.show();
        WindowManager.LayoutParams  lp= alertDialog.getWindow().getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
       int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;

        //定义宽度
        lp.width=(int) (displayWidth* 0.60f + 0.5f);
        //定义高度
        //lp.height=(int) (displayHeight* 0.45f + 0.5f);
        alertDialog.getWindow().setAttributes(lp);

        return alertDialog;

    }

    /**
     * 只为显示文字
     * @param context
     * @param setTitle
     * @param callBack
     * @return
     */
    public static CustomDialog showDialog_Text(final Context context, String setTitle,String message,final DialogCallBack callBack ){

        Activity activity = (Activity) context;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        TextView textView = new TextView(context);
        textView.setText(setTitle);
        textView.setTextColor(context.getResources().getColor(R.color.black));
        builder.setTitle(setTitle); //设置标题
//        TextView textViewfu = new TextView(context);
//        textViewfu.setText(message);
//        textViewfu.setTextColor(context.getResources().getColor(R.color.black));
//        String s = textViewfu.getText().toString();
        builder.setMessage(message); //设置内容



        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                    //LogUtils.i("被选中的是"+which+"号"+"内容是"+string.get(which));
                callBack.confirm();
                dialog.dismiss();
            }

        });


        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        callBack.cancel();
                        dialog.dismiss();
                    }
                });
        // builder.create().show();
        //设置窗口的大小
        CustomDialog alertDialog = builder.create();
        builder.setCanl(true);

        alertDialog.show();
        WindowManager.LayoutParams  lp= alertDialog.getWindow().getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;

        //定义宽度
        lp.width=(int) (displayWidth* 0.75f + 0.5f);
        //定义高度
        //lp.height=(int) (displayHeight* 0.50f + 0.5f);
        alertDialog.getWindow().setAttributes(lp);

        return alertDialog;

    }
    /**
     * 显示 银行那一块
     * @param context
     * @param setTitle
     * @param string
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static CustomDialog showDialogLinerLayout(final Context context, String setTitle, final List<String> string){
        Activity activity = (Activity) context;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        TextView textView = new TextView(context);
        textView.setText(setTitle);
        textView.setTextColor(context.getResources().getColor(R.color.black));
        builder.setTitle(setTitle); //设置标题
//        TextView textViewfu = new TextView(context);
//        textViewfu.setText(setMessage);
//        textViewfu.setTextColor(context.getResources().getColor(R.color.black));
//        String s = textViewfu.getText().toString();
//        builder.setMessage(s); //设置内容


        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
       // android:background="@drawable/shape_white"
        relativeLayout.setBackgroundResource(R.drawable.shape_white);
        //relativeLayout.setBackground(context.getDrawable(R.drawable.shape_white));
        relativeLayout.setLayoutParams(layoutParams);

        View inflate = View.inflate(context, R.layout.dialog_linelayout, relativeLayout);
        final LinearLayout dialog_ll = (LinearLayout) inflate.findViewById(R.id.dialog_ll);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i <string.size() ; i++) {
            TextView view = new TextView(context);
            view.setLayoutParams(params);
            view.setPadding(30,10,30,10);
            view.setGravity(Gravity.CENTER_HORIZONTAL);
            view.setText(string.get(i));
            view.setTextColor(context.getResources().getColor(R.color.black));


//            Drawable nav_up=context.getResources().getDrawable(R.drawable.my_radiobutton_selector);
//            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//            radioButton.setCompoundDrawables(null, null, nav_up, null);

            dialog_ll.addView(view);
        }

        //设置中间布局
        builder.setContentView(inflate);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //LogUtils.i("集合长度"+string.size()+"集合内容"+string+"选择号码 是"+which);

                //如果没有被选中的条目
                            dialog.dismiss();
                            return;
                //设置你的操作事项
                //点的确定
               // listener.confirm(selectorButton,CONFIRM);
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
       // builder.create().show();
        //设置窗口的大小
        CustomDialog alertDialog = builder.create();
            alertDialog.show();
        WindowManager.LayoutParams  lp= alertDialog.getWindow().getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
       int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;

        //定义宽度
        lp.width=(int) (displayWidth* 0.90f + 0.5f);
        //定义高度
        lp.height=(int) (displayHeight* 0.70f + 0.5f);
        //alertDialog.getWindow().setAttributes(lp);

        return alertDialog;

    }
}
