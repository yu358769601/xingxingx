package com.xinxinxuedai.Utils.NoHttp.controlQueue;

import com.xinxinxuedai.Utils.NoHttp.queue.Request;
import com.xinxinxuedai.Utils.NoHttp.queue.YolandaLinkedQueue;
import com.xinxinxuedai.Utils.NoHttp.task.MultiAsynctaskNetwork;
import com.xinxinxuedai.Utils.NoHttp.task.NetworkInterface;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 17:44 . 2016年12月28日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class ControlQueue extends YolandaLinkedQueue<Request> {

    static ControlQueue sControlQueue;




    public ControlQueue(int poolSize) {
        super(poolSize);
    }
    static NetworkInterface mCallBack;

    public static ControlQueue getControlQueue(int poolSize, NetworkInterface callBack){
        mCallBack = callBack;
        if (sControlQueue==null)
            return sControlQueue = new ControlQueue(poolSize);
         return sControlQueue;
        }


    @Override
    public void getData(Request request) {
        new MultiAsynctaskNetwork(request.getUrl(), new NetworkInterface() {
            @Override
            public void onResult(String result) {
                mCallBack.onResult(result);
            }

            @Override
            public void onUpData(int i) {
                mCallBack.onUpData(i);
            }
        });
    }
}




