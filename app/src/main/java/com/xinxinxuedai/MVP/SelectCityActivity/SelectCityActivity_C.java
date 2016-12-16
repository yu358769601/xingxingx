package com.xinxinxuedai.MVP.SelectCityActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:45 . 2016年12月08日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface SelectCityActivity_C extends BaseMVPinterFace_CallBack{
    /**
     * 返回 省市信息
     * @param sheng
     * @param shi
     */
    void getData(String sheng , String shi);
}
