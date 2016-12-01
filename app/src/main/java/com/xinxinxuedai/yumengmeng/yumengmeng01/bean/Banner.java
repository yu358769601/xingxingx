package com.xinxinxuedai.yumengmeng.yumengmeng01.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 16:37 . 2016年06月17日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public class Banner implements Serializable {



    public String id;
    public String repair_id;
    public byte[]  file_path;
    public String log;
    public Object create_time;
    public String update_time;
    public String order_id;
    public String tag_id;

    public Banner(byte[]  file_path) {
        this.file_path = file_path;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", repair_id='" + repair_id + '\'' +
                ", file_path=" + Arrays.toString(file_path) +
                ", log='" + log + '\'' +
                ", create_time=" + create_time +
                ", update_time='" + update_time + '\'' +
                ", order_id='" + order_id + '\'' +
                ", tag_id='" + tag_id + '\'' +
                '}';
    }
}
