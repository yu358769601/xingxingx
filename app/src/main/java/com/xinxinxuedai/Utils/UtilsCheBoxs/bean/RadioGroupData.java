package com.xinxinxuedai.Utils.UtilsCheBoxs.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 13:45 . 2016年12月30日
 * 描述:条目上面的属性
 * <p>
 * <p>
 * 备注:
 */

public class RadioGroupData {

    public double money;
    public String date;
    public int tag;

    public RadioGroupData(double money, String date, int tag) {
        this.money = money;
        this.date = date;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "RadioGroupData{" +
                "money=" + money +
                ", date='" + date + '\'' +
                ", tag=" + tag +
                '}';
    }
}
