package com.xinxinxuedai.MVP.xingxingxinyongActivity.presenter;

import android.content.Context;

import com.xinxinxuedai.MVP.xingxingxinyongActivity.contract.xingxingxinyongContract;
import com.xinxinxuedai.MVP.xingxingxinyongActivity.model.xingxingxinyongModel;
import com.xinxinxuedai.bean.GetInfo;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:08 . 2017年01月09日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public class xingxingxinyongPresenter implements xingxingxinyongContract.Presenter {
    //给View设置数据
    xingxingxinyongContract.View view;
    private final xingxingxinyongModel mXingxingxinyongModel;


    public xingxingxinyongPresenter(Context context, xingxingxinyongContract.View view){
        this.view = view;
        mXingxingxinyongModel = new xingxingxinyongModel(context);
    }

    @Override
    public void getData() {
        mXingxingxinyongModel.getData(new xingxingxinyongContract.CallBackData<GetInfo>() {
            @Override
            public void callBack(GetInfo getInfo) {
                view.setData(getInfo);
            }
        });
    }
}
