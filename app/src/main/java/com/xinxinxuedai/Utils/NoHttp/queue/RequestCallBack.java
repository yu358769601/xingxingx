package com.xinxinxuedai.Utils.NoHttp.queue;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 17:52 . 2016年12月28日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface RequestCallBack {
    /**
     * 成功
     * @param sccess
     */
    void onSuccess(String sccess);

    /**
     * 失败
     * @param error
     */
    void onError(String error);
}
