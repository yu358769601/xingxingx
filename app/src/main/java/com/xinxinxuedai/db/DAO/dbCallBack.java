package com.xinxinxuedai.db.DAO;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 10:19 . 2016年12月23日
 * 描述:数据库查询_回调
 * <p>
 * <p>
 * 备注:
 */

public interface dbCallBack<T> {
    /**
     * 获取数据库信息成功
     * @param t
     */
    void getDataSuccess(T t);

    /**
     * 获取数据库信息失败
     * @param error
     */
    void getDataError(String error);

    /**
     * 获取所有的信息成功
     * @param arrayList
     */
    void getAllDataSuccess(ArrayList<T> arrayList);
}
