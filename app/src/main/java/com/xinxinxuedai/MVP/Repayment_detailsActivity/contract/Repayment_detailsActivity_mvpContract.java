package com.xinxinxuedai.MVP.Repayment_detailsActivity.contract;

import android.widget.LinearLayout;

import com.xinxinxuedai.Utils.UtilsCheBoxs.bean.RadioGroupData;
import com.xinxinxuedai.bean.RepaymentList;

import java.util.ArrayList;

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
        void closeActivity();
    }

    public interface Presenter {
    }

    public interface Model {
        void setData(LinearLayout repayment_details_rg,GetModelCallBack callBack);
        void sub(int postion, RepaymentList.DataBean dataList, CallBackMsg callBack);

        void setMoney(double interest_money);
        ArrayList<RadioGroupData> getList();
    }

    public interface GetModelCallBack{
        void getmoney(double v);

    }

    interface CallBackMsg{
        void chenggong(String s);
        void  shibai(String s);
    }
}
