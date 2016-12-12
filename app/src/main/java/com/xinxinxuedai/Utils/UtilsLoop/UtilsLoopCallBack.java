package com.xinxinxuedai.Utils.UtilsLoop;

import android.view.View;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 16:28 . 2016年12月12日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface UtilsLoopCallBack {

    void onSucceed();

    void onError(View view , int numTag,String rroreInfo);
}
