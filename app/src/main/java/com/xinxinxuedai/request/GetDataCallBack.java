package com.xinxinxuedai.request;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 11:45 . 2016年12月14日
 * 描述:获取到缓存数据
 * <p>s
 * <p>
 * 备注:
 */

public interface GetDataCallBack<T> {
    /**
     * 设置缓存数据
     * @param data
     */
    void getData(T data);
}
