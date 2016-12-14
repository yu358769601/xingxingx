package com.xinxinxuedai.MVP.LoanApplicationActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.bean.GetLoanDetail;

import java.util.ArrayList;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:39 . 2016年12月02日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface LoanApplicationActivity_CallBack extends BaseMVPinterFace_CallBack{
//    /**
//     * 从另外一个接口返回的  选择的号码 和 选择号码对应的 内容
//     * @param selectNum 选择的号码
//     * @param selectNumInfo 对应的选择内容
//     */
//    void getSelectInfo1(int selectNum, String selectNumInfo);
//    void getSelectInfo2(int selectNum, String selectNumInfo);
//    void getSelectInfo3(int selectNum, String selectNumInfo);

    void getTextInfo4(String TextInfo4);

    /**
     * 回调到主界面去show
     * @param strings
     */
    void showDialog1(ArrayList<String> strings,String title);
    /**
     * 回调到主界面去show
     * @param strings
     */
    void showDialog2(ArrayList<String> strings,String title);
    /**
     * 回调到主界面去show
     * @param strings
     */
    void showDialog3(ArrayList<String> strings,String title);

    /**
     * 网络数据回来了设置回显数据
     * @param callBackData
     */
    void setCallBackData(GetLoanDetail callBackData);
}
