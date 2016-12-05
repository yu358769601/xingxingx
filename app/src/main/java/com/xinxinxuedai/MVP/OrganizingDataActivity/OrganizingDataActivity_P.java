package com.xinxinxuedai.MVP.OrganizingDataActivity;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.Utils.UtilsToast;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:44 . 2016年12月05日
 * 描述:完善信息_P
 * <p>
 * <p>
 * 备注:
 */

public class OrganizingDataActivity_P extends BaseMvp<OrganizingDataActivity_C> implements OrganizingDataActivity_M{
static OrganizingDataActivity_P mOrganizingDataActivity_p;
    Context context;
    public OrganizingDataActivity_P(Context context) {
        this.context = context;
    }

    @Override
    public void setCallBack(OrganizingDataActivity_C organizingDataActivity_c) {

    }
    public static OrganizingDataActivity_P getmOrganizingDataActivity_p(Context context){
        if (null == mOrganizingDataActivity_p){
            return mOrganizingDataActivity_p = new OrganizingDataActivity_P(context);
        }
        return mOrganizingDataActivity_p;
    }


    /**
     * 获取 这个页面上所有的 textView
     *
     * @param textViews
     */
    @Override
    public void getTextViews(List<TextView> textViews, EditText editText) {
        if (textViews.size()==0||null==textViews){
            return;
        }
        for (int i = 0; i < textViews.size(); i++) {
            TextView textView = textViews.get(i);
            int tag = (int) textView.getTag();

            //是否为空
            if (UtilsMyText.isEmptys(textView)){
                switch (tag){
                    case 1:
                        UtilsToast.showToast(context, "申请借款用途为空");
                    return;
                    case 2:
                        UtilsToast.showToast(context, "申请借款恶额度为空");
                        return;
                    case 3:
                        UtilsToast.showToast(context, "申请借款期限为空");
                     return;
                }

            }
        }
        if (UtilsMyText.isEmptys(editText)){
            UtilsToast.showToast(context, "现在住址为空");
            return;
        }
        UtilsToast.showToast(context, "正在发送请求~");


    }
}
