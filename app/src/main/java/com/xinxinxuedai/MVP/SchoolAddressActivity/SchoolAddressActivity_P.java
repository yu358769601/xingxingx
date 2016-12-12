package com.xinxinxuedai.MVP.SchoolAddressActivity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopCallBack;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopTextView;
import com.xinxinxuedai.Utils.UtilsToast;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 16:09 . 2016年12月12日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class SchoolAddressActivity_P extends BaseMvp<SchoolAddressActivity_C> implements SchoolAddressActivity_M{
    static SchoolAddressActivity_P sSchoolAddressActivity_p;
    Context context;
    public SchoolAddressActivity_P(Context context) {
        this.context = context;
    }

    public static SchoolAddressActivity_P getP(Context context){
        if (sSchoolAddressActivity_p==null)
            sSchoolAddressActivity_p = new SchoolAddressActivity_P(context);
        return sSchoolAddressActivity_p;
    }

    SchoolAddressActivity_C schoolAddressActivity_c;
    @Override
    public void setCallBack(SchoolAddressActivity_C schoolAddressActivity_c) {
        this.schoolAddressActivity_c = schoolAddressActivity_c;
    }


    @Override
    public void setSub(ArrayList<TextView> sub , ArrayList<String> strings) {

        UtilsLoopTextView.startLoop(sub, strings,new UtilsLoopCallBack() {
            @Override
            public void onSucceed() {
               // UtilsToast.showToast(context, "成功");
            }

            @Override
            public void onError(View view, int numTag,String strings) {
                    UtilsToast.showToast(context, strings);
                    return;
                }


        });
        //访问网络
        UtilsToast.showToast(context, "访问网络中~");


    }
}
