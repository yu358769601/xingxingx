package com.xinxinxuedai.Utils.NoHttp.controlQueue;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 17:44 . 2016年12月28日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class ControlQueue {
    static ControlQueue sControlQueue;

    public ControlQueue() {
    }

    public static ControlQueue getControlQueue(){
        if (sControlQueue==null)
            return sControlQueue = new ControlQueue();
         return sControlQueue;
        }
    }




