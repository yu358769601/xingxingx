package com.xinxinxuedai.MVP.Repayment_detailsActivity.model;

import android.content.Context;
import android.widget.LinearLayout;

import com.xinxinxuedai.MVP.Repayment_detailsActivity.contract.Repayment_detailsActivity_mvpContract;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsRadio.ItemNumCallBack;
import com.xinxinxuedai.Utils.UtilsRadio.UtilsRadio;
import com.xinxinxuedai.Utils.UtilsRadio.bean.RadioGroupData;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:03 . 2016年12月30日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public class Repayment_detailsActivity_mvpModel implements Repayment_detailsActivity_mvpContract.Model {
    int oldPostion = -1;
    Context context;
    public Repayment_detailsActivity_mvpModel(Context context) {
        this.context = context;
    }

    @Override
    public void setData(LinearLayout repayment_details_rg, final Repayment_detailsActivity_mvpContract.GetModelCallBack callBack) {
        final ArrayList<RadioGroupData> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new RadioGroupData(i,"无视"+i,1));
        }
        UtilsRadio utilsRadio = new UtilsRadio();
        utilsRadio.setRadioGroupData(context, repayment_details_rg, list, new ItemNumCallBack<RadioGroupData>() {
            @Override
            public void getNum(int postion, RadioGroupData data) {
                if (oldPostion!=postion){
                    oldPostion= postion;
                    LogUtils.i("点了多少"+postion +"内容是"+data);
                    callBack.getmoney(data.money);
                }

            }
        });
    }

}
