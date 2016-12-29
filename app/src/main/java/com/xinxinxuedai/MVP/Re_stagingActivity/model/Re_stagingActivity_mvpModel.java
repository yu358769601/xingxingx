package com.xinxinxuedai.MVP.Re_stagingActivity.model;

import android.content.Context;

import com.xinxinxuedai.MVP.Re_stagingActivity.contract.Re_stagingActivity_mvpContract;

import java.util.ArrayList;

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
        strings.add("28");
        strings.add("56");
        strings.add("84");
        strings.add("112");
        dialog.getList(strings);
    }


}
