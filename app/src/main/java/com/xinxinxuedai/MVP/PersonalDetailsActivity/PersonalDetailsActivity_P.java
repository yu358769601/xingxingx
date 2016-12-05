package com.xinxinxuedai.MVP.PersonalDetailsActivity;

import android.content.Context;
import android.widget.EditText;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.Utils.UtilsToast;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 12:38 . 2016年12月05日
 * 描述:个人信息_P
 * <p>
 * <p>
 * 备注:
 */

public class PersonalDetailsActivity_P extends BaseMvp<PersonalDetailsActivity_C> implements PersonalDetailsActivity_M{


    static  PersonalDetailsActivity_P mPersonalDetailsActivity_p;
    Context context;
    public PersonalDetailsActivity_P(Context context) {
        this.context = context;
    }

    public static PersonalDetailsActivity_P getmPersonalDetailsActivity_p(Context context){
        if(null==mPersonalDetailsActivity_p){
            return mPersonalDetailsActivity_p = new PersonalDetailsActivity_P(context);
        }else{
            return mPersonalDetailsActivity_p;
        }
    }
    PersonalDetailsActivity_C personalDetailsActivity_c;

    @Override
    public void setCallBack(PersonalDetailsActivity_C personalDetailsActivity_c) {
        this.personalDetailsActivity_c = personalDetailsActivity_c;
    }


    /**
     * 获取 这个页面上所有的 editText 还有一个 radiobutton 选择的号码
     *
     * @param editTexts
     * @param select
     */
    @Override
    public void getEdtexts(List<EditText> editTexts, int select) {
        if (editTexts.size()==0||null == editTexts){
            return;
        }
        EditText editText1 = new EditText(context);
        EditText editText2 = new EditText(context);
        EditText editText3 = new EditText(context);
        EditText editText4 = new EditText(context);
        EditText editText5 = new EditText(context);
        EditText editText6 = new EditText(context);
        for (int i = 0; i < editTexts.size(); i++) {
            EditText editText = editTexts.get(i);
            //String string = editText.getText().toString();
            int tag = (int) editText.getTag();



            //判断是否为空
            if (UtilsMyText.isEmptys(editText)){
                switch (tag) {
                    case 1:
                        UtilsToast.showToast(context, "姓名为空");
                        return;
                    case 2:
                        UtilsToast.showToast(context, "身份证为空");
                        return;
                    case 3:
                        UtilsToast.showToast(context, "微信号为空");
                        return;
                    case 4:
                        UtilsToast.showToast(context, "家庭地址为空");
                        return;
                    case 5:
                        UtilsToast.showToast(context, "紧急联系人姓名为空");
                        return;
                    case 6:
                        UtilsToast.showToast(context, "紧急联系人电话为空");
                        return;
                }
            }


        }
        for (int i = 0; i < editTexts.size(); i++) {
            EditText editText = editTexts.get(i);
            //String string = editText.getText().toString();
            int tag = (int) editText.getTag();

            //判断长度是否够用
            switch (tag) {
                case 1:
                    break;
                case 2:
                    if (UtilsMyText.getLengh(editText)<=11){
                        UtilsToast.showToast(context, "身份证长度不正确");
                        return;
                    }

                case 3:
                    break;
                case 4:
                    if (UtilsMyText.getLengh(editText)<=8){
                        UtilsToast.showToast(context, "家庭住址长度不正确");
                        return;
                    }
                case 5:
                    break;
                case 6:
                    if (UtilsMyText.getLengh(editText)<=11){
                        UtilsToast.showToast(context, "紧急联系人电话长度小于11位");
                        return;
                    }
            }
        }


        if (select!=-1){
            UtilsToast.showToast(context, "网络请求中~");
        }

    }
}
