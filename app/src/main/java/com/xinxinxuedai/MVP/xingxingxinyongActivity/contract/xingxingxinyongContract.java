package com.xinxinxuedai.MVP.xingxingxinyongActivity.contract;

import com.xinxinxuedai.bean.GetInfo;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:08 . 2017年01月09日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public interface xingxingxinyongContract {
    public interface View {
        void setData(GetInfo getInfo);
    }

    public interface Presenter {
        void getData();
    }

    public interface Model<T> {
        void getData(CallBackData<T> tCallBackData);
    }
    interface CallBackData<T>{
        void callBack(T t);
    }
}
