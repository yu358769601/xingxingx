package com.xinxinxuedai.MVP.Repayment_detailsActivity.contract;

import android.widget.LinearLayout;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:03 . 2016年12月30日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public interface Repayment_detailsActivity_mvpContract {
    public interface View {
        void CallBack(double v);
    }

    public interface Presenter {
    }

    public interface Model {
        void setData(LinearLayout repayment_details_rg,GetModelCallBack callBack);
    }

    public interface GetModelCallBack{
        void getmoney(double v);
    }
}
