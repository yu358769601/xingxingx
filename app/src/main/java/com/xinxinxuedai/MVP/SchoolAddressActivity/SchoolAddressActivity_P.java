package com.xinxinxuedai.MVP.SchoolAddressActivity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopCallBack;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopTextView;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.SetInfo2;
import com.xinxinxuedai.request.GetInfo_Request;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.SetInfo2_Request;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 16:09 . 2016年12月12日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class SchoolAddressActivity_P extends BaseMvp<SchoolAddressActivity_C> implements SchoolAddressActivity_M{
    Context context;
    public SchoolAddressActivity_P(Context context) {
        this.context = context;
    }


    SchoolAddressActivity_C schoolAddressActivity_c;
    @Override
    public void setCallBack(SchoolAddressActivity_C schoolAddressActivity_c) {
        this.schoolAddressActivity_c = schoolAddressActivity_c;
    }


    @Override
    public void setSub(final ArrayList<TextView> sub , final ArrayList<String> strings, final String [] array) {

        UtilsLoopTextView.startLoop(sub, strings,new UtilsLoopCallBack() {
            @Override
            public void onSucceed() {
                //访问网络
               // UtilsToast.showToast(context, "访问网络中~");
                Hashtable<String, String> hashtable =
                        UtilsHashtable.getHashtable();

//                array.add(schooladdress_tv);
//                array.add(schooladdress_et_1);
//                array.add(schooladdress_tv2);
//                array.add(schooladdress_et_2);
//                ArrayList<TextView> views = UtilsLoopTextView.addTagList(array);
//                final ArrayList<String> strings = new ArrayList<>();
//                strings.add("学校名为空");
//                strings.add("入学年份为空");
//                strings.add("学校所在地区未选择");
//                strings.add("门牌号为空");
                //借款人学校
                hashtable.put("loan_school_name",sub.get(0).getText().toString().trim());
                //入学时间
                hashtable.put("loan_admission_school",sub.get(1).getText().toString().trim());
                //借款人现住址
                hashtable.put("loan_present_address",sub.get(3).getText().toString().trim());

                //导员姓名
                hashtable.put("loan_tutor","");
                //市ID
                hashtable.put("loan_city",array[1]);
                //省ID
                hashtable.put("loan_province",array[0]);
                //导员联系方式
                hashtable.put("loan_tutor_mobile","");

                SetInfo2_Request.request(context, hashtable, new NetWorkCallBack<SetInfo2>() {
                    @Override
                    public void onSucceed(SetInfo2 info2,int dataMode) {

                        UtilsToast.showToast(context,info2.message);

                        //关掉界面
                        schoolAddressActivity_c.closeActivity();
                    }

                    @Override
                    public void onError(String jsonObject) {
                        UtilsToast.showToast(context,jsonObject);
                    }
                });
            }

            @Override
            public void onError(View view, int numTag,String strings) {
                    UtilsToast.showToast(context, strings);
                    return;
                }


        });


    }

    @Override
    public void getCallBackData() {
        GetInfo_Request.request(context, new NetWorkCallBack<GetInfo>() {
            @Override
            public void onSucceed(GetInfo getInfo,int dataMode) {
                schoolAddressActivity_c.setCallBackData(getInfo);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }
}
