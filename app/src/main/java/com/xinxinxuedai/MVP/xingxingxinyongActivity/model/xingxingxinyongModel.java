package com.xinxinxuedai.MVP.xingxingxinyongActivity.model;

import android.content.Context;

import com.xinxinxuedai.MVP.xingxingxinyongActivity.contract.xingxingxinyongContract;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.request.GetInfo_Request;
import com.xinxinxuedai.request.NetWorkCallBack;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:08 . 2017年01月09日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public class xingxingxinyongModel implements xingxingxinyongContract.Model<GetInfo> {
    Context context;
    public xingxingxinyongModel(Context context) {
      this.context = context;
    }

    @Override
    public void getData(final xingxingxinyongContract.CallBackData<GetInfo> getInfoCallBackData) {
        GetInfo_Request.request(context, new NetWorkCallBack<GetInfo>() {
            @Override
            public void onSucceed(GetInfo getInfo, int dataMode) {
                getInfoCallBackData.callBack(getInfo);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }
}
