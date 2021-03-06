package com.xinxinxuedai.MVP.Re_stagingActivity.presenter;

import android.content.Context;
import android.view.View;

import com.xinxinxuedai.MVP.Re_stagingActivity.contract.Re_stagingActivity_mvpContract;
import com.xinxinxuedai.MVP.Re_stagingActivity.model.Re_stagingActivity_mvpModel;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.ui.Re_stagingActivity;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 15:52 . 2016年12月29日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public class Re_stagingActivityPresenter implements Re_stagingActivity_mvpContract.Presenter {

    Re_stagingActivity_mvpContract.Model modelInter;
    //给View 的结果
    Re_stagingActivity_mvpContract.View viewCall;
    Context context;

    public Re_stagingActivityPresenter(AppContext application, Re_stagingActivity re_stagingActivity) {

    }


    @Override
    public void method(Context context, Re_stagingActivity_mvpContract.View viewCall) {
        //重要的 子类对象引用到父类  多态
        modelInter = new Re_stagingActivity_mvpModel(context);
        this.context =context;
        this.viewCall =viewCall;
    }

    /**
     * 点击了的View
     *
     * @param view
     */
    @Override
    public void SetOnClick(View view) {
        switch ((int)view.getTag()){
            case 1:
            //点了 选择再分期的按钮
                modelInter.SetDialog(new Re_stagingActivity_mvpContract.Dialog() {
                    @Override
                    public void getList(ArrayList<String> list) {
                        viewCall.getData(list);
                    }
                });
            break;
        }
    }


    public void getData() {
        modelInter.getInfo(new Re_stagingActivity_mvpContract.GetInfoData() {
            @Override
            public void CallData(GetInfo getInfo) {
                viewCall.InfoData(getInfo);
            }
        });
    }

    /**
     * 点了提交
     * @param zaiFenQDay
     * @param dataList
     */
    public void subClick(String zaiFenQDay, RepaymentList.DataBean dataList) {
        LogUtils.i("点了提交再分期申请"+"内容是"+"多少天"+zaiFenQDay+"\t"+"id"+dataList.id);
        modelInter.sub(zaiFenQDay, dataList, new Re_stagingActivity_mvpContract.CallBack() {
            @Override
            public void close() {
                viewCall.closeActivity();
            }
        });
    }
}
