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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.view.dialog.CustomDialog;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 20:22 . 2016年12月04日
 * 描述:零用金的 dialog
 * <p>
 * <p>
 * 备注:
 */

public class UtilsDialog {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static CustomDialog showDialog(final Context context, String setTitle, final List<String> string, final UtilsDialogCallBack callBack){
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
        //relativeLayout.setBackgroundResource(R.drawable.shape_white);
        relativeLayout.setBackground(context.getDrawable(R.drawable.shape_white));
        relativeLayout.setLayoutParams(layoutParams);
        View inflate = View.inflate(context, R.layout.dialog_radiogroup, relativeLayout);
        final RadioGroup dialog_rg = (RadioGroup) inflate.findViewById(R.id.dialog_rg);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i <string.size() ; i++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setLayoutParams(params);
            radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            radioButton.setPadding(30,30,30,30);
            radioButton.setGravity(Gravity.CENTER_VERTICAL);
            radioButton.setText(string.get(i));
            radioButton.setTextColor(context.getResources().getColor(R.color.black));


            Drawable nav_up=context.getResources().getDrawable(R.drawable.my_radiobutton_selector);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            radioButton.setCompoundDrawables(null, null, nav_up, null);

            dialog_rg.addView(radioButton);
        }

        builder.setContentView(inflate);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //LogUtils.i("集合长度"+string.size()+"集合内容"+string+"选择号码 是"+which);

                //如果没有被选中的条目
                if (-1 == dialog_rg.getCheckedRadioButtonId()){
                    UtilsToast.showToast(context,"未选择任何条目");
                }else{
                    for (int i = 0; i < dialog_rg.getChildCount(); i++) {
                        RadioButton childAt = (RadioButton) dialog_rg.getChildAt(i);
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
        lp.width=(int) (displayWidth* 0.90f + 0.5f);
        //定义高度
        lp.height=(int) (displayHeight* 0.70f + 0.5f);
        //alertDialog.getWindow().setAttributes(lp);

        return alertDialog;











//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setIcon(R.drawable.ic_launcher);
//
//        builder.setTitle("请选择性别");
//        //final String[] sex = {"男", "女", "未知性别"};
//        //    设置一个单项选择下拉框
//        /**
//         * 第一个参数指定我们要显示的一组下拉单选框的数据集合
//         * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
//         * 第三个参数给每一个单选项绑定一个监听器
//         */
////        builder.setSingleChoiceItems(sex, 1, new DialogInterface.OnClickListener()
////        {
////            @Override
////            public void onClick(DialogInterface dialog, int which)
////            {
////                //Toast.makeText(context, "性别为：" + sex[which], Toast.LENGTH_SHORT).show();
////            }
////        });
//
//        View inflate = View.inflate(context, R.layout.dialog_radiogroup, null);
//        RadioGroup dialog_rg = (RadioGroup) inflate.findViewById(R.id.dialog_rg);
//
//        builder.setView(inflate);
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//
//            }
//        });
//
//        //builder.show();
//        //设置窗口的大小
//        AlertDialog alertDialog = builder.create();
//       alertDialog.show();
//        WindowManager.LayoutParams  lp= alertDialog.getWindow().getAttributes();
//        lp.width=800;//定义宽度
//        lp.height=800;//定义高度
//        alertDialog.getWindow().setAttributes(lp);

    }
}