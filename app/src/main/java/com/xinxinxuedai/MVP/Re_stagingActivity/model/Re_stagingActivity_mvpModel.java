package com.xinxinxuedai.MVP.Re_stagingActivity.model;

import android.content.Context;

import com.xinxinxuedai.MVP.Re_stagingActivity.contract.Re_stagingActivity_mvpContract;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.bean.ApplyForZFQ;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.request.ApplyForZFQ_Request;
import com.xinxinxuedai.request.GetInfo_Request;
import com.xinxinxuedai.request.NetWorkCallBack;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 15:52 . 2016年12月29日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public class Re_stagingActivity_mvpModel implements Re_stagingActivity_mvpContract.Model {
    Context context;
    public Re_stagingActivity_mvpModel(Context context) {
        this.context =context;
    }





    @Override
    public void SetDialog(Re_stagingActivity_mvpContract.Dialog dialog) {
      ArrayList<String> strings=  new ArrayList<>();
       // strings.add("28");
        strings.add("56");
        strings.add("84");
        strings.add("112");
        dialog.getList(strings);
    }

    @Override
    public void getInfo(final Re_stagingActivity_mvpContract.GetInfoData getInfoData) {
        GetInfo_Request.request(context, new NetWorkCallBack<GetInfo>() {
            @Override
            public void onSucceed(GetInfo getInfo, int dataMode) {
                getInfoData.CallData(getInfo);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }

    @Override
    public void sub(String zaiFenQDay, RepaymentList.DataBean dataList, final Re_stagingActivity_mvpContract.CallBack callBack) {
        Hashtable<String, String> hashtable =
                UtilsHashtable.getHashtable();
        hashtable.put("id", dataList.id + "");
        hashtable.put("again_term", zaiFenQDay);

        ApplyForZFQ_Request.request(context, hashtable, new NetWorkCallBack<ApplyForZFQ>() {
            @Override
            public void onSucceed(ApplyForZFQ applyForZFQ, int dataMode) {
                UtilsToast.showToast(context, applyForZFQ.message);
                callBack.close();
            }

            @Override
            public void onError(String jsonObject) {

            }
        });

    }

}
