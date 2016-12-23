package com.xinxinxuedai.db.DAO;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 10:50 . 2016年12月23日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class dbCallBackHelper<T> implements dbCallBack<T>{


    /**
     * 获取数据库信息成功
     *
     * @param t
     */
    @Override
    public void getDataSuccess(T t) {

    }

    /**
     * 获取数据库信息失败
     *
     * @param error
     */
    @Override
    public void getDataError(String error) {

    }

    /**
     * 获取所有的信息成功
     *
     * @param arrayList
     */
    @Override
    public void getAllDataSuccess(ArrayList<T> arrayList) {

    }
}
