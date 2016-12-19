package com.xinxinxuedai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.base.BaseListViewAdapter_04;
import com.xinxinxuedai.bean.RePayMent;
import com.xinxinxuedai.view.MyListView;

import java.util.List;


/**
 * Created by 35876 于萌萌
 * 创建日期: 16:00 . 2016年10月08日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */ //我的行程adapter
public class MyListView_04_more extends BaseListViewAdapter_04<RePayMent.DataBean> implements MyListView.OnLoadListener {
    //public String net = "http://app.beta.9ac.com.cn/";
    ListViewLoadMore mListViewLoadMore;
    int type;
    ListViewCallBack mListViewCallBack;
    public MyListView_04_more(Context context, int type, List datas,ListViewCallBack mListViewCallBack) {
        super(context, type, datas);
        this.type = type;
        this.mListViewCallBack = mListViewCallBack;
    }

    public void setListViewLoadMore(ListViewLoadMore mListViewLoadMore) {
        this.mListViewLoadMore = mListViewLoadMore;
        mListViewLoadMore.listViewLoadMore(type);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    /**
     * 基类不知道 你有什么样的布局 所以是抽象的
     *item
     * @param item
     * @param position
     * @return
     */
    @Override
    public int getViewType(RePayMent.DataBean item, int position) {

        return R.layout.xuedai_button_3;
    }

    @Override
    public void convert(MyViewHolder_04 helper, RePayMent.DataBean item, final int position) {
        //int i = Integer.parseInt(item.delay);
        int i1 = getViewType(item, position);
        if (i1 == R.layout.xuedai_button_3) {
//            String id = item.id;
//            //还款服务费
//            item.interest_money;
//            //具备再分期条件0 不具备 1 具备
//            item.again_flag;
//            //再分期id
//            item.again_id;
//            //还款期数
//            item.current_flag;
//            //还款本金
//            item.money;
//            //违约金
//            item.weiyue_money;
//            //计划还款时间
//            item.plan_date;
//            //真实还款时间
//            item.real_data;
//            //真实还款金额
//            item.real_money;
//            //还款状态 0 待还款  1 已还款 2 逾期 3提前还款 4坏账5减免
//            item.repay_status;
//            //还款利息
//            item.service_fee;
//            //借款id
//            item.user_loan_id;
            //图
            helper.initImage_status(R.id.xuedai_button3_iv,item.repay_status);
            //应还金额
            helper.initText_hint(R.id.xuedai_button3_tv1,item.real_money);
            //已还金额
            helper.initText_hint(R.id.xuedai_button3_tv2,item.money);
            //分期
            helper.initText_hint(R.id.xuedai_button3_tv3,item.current_flag);
            //还款按钮
            TextView textView1 = helper.initText(R.id.xuedai_button3_tv5, "");
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListViewCallBack.getRepayment(position);
                }
            });
            //计划还款日
            helper.initText_hint(R.id.xuedai_button3_tv4,item.plan_date);
            //再分期提示
            helper.initText(R.id.xuedai_button3_tv6,"");
            //再分期按钮
            TextView textView = helper.initText(R.id.xuedai_button3_tv7, "");
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListViewCallBack.getZaifenqi(position);
                }
            });
            //是否显示下面
            helper.initLinearLayout_Show(R.id.ll2,item.again_flag);


        }


    }


    @Override
    public void onLoad() {
        mListViewLoadMore.listViewLoadMore(type);
    }

}
