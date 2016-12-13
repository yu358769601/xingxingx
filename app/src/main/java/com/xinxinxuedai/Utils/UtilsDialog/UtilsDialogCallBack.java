package com.xinxinxuedai.Utils.UtilsDialog;

/**
 * Created by 35876 于萌萌
 * 创建日期: 9:15 . 2016年12月05日
 * 描述:按钮的回调 选中什么按钮了
 * <p>
 * <p>
 * 备注:
 */

public interface UtilsDialogCallBack {
    /**
     * 点了确定并且 已经有了选择的号码 之后产生的回调
     * @param selectNum 选择的号码
     * @param selectNumInfo 号码 里面对应的内容
     */
    void RadioGroupNum(int selectNum,String selectNumInfo);


}
