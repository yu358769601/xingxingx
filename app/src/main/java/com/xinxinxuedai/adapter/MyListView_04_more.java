package com.xinxinxuedai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.base.BaseListViewAdapter_04;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.view.MyListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 35876 于萌萌
 * 创建日期: 16:00 . 2016年10月08日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */ //我的行程adapter
public class MyListView_04_more extends BaseListViewAdapter_04<RepaymentList.DataBean> implements MyListView.OnLoadListener {
    //public String net = "http://app.beta.9ac.com.cn/";
    ListViewLoadMore mListViewLoadMore;
    int type;
    ListViewCallBack mListViewCallBack;
    //期限 最大值
    int loan_term;

    // 是否显示还款按钮
    boolean tag = true;

    int frist = -1;
    public MyListView_04_more(Context context, int type, List datas, int loan_term, ListViewCallBack mListViewCallBack) {
        super(context, type, datas);
        this.type = type;
        this.mListViewCallBack = mListViewCallBack;
        this.loan_term = loan_term;
        getFristsub(datas);
    }

    /**
     * 设置第一个按钮的还款是否显示
     * @param datas
     */
    public void getFristsub(List datas) {
        ArrayList<RepaymentList.DataBean> data =(ArrayList<RepaymentList.DataBean>)datas;
        for (int i = 0; i < data.size(); i++) {
            RepaymentList.DataBean dataBean = data.get(i);
            int repay_status = dataBean.repay_status;
            if (repay_status==0){
              frist = i;

                return;
            }
        }
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
    public int getViewType(RepaymentList.DataBean item, int position) {

        return R.layout.xuedai_button_3;
    }

    @Override
    public void convert(MyViewHolder_04 helper, RepaymentList.DataBean item, final int position) {
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
//            //真实还款金额 已还
//            item.real_money;
//            //还款状态 0 待还款  1 已还款 2 逾期 3提前还款 4坏账5减免
//            item.repay_status;
//            //还款利息
//            item.service_fee;
//            //借款id
//            item.user_loan_id;
            //图
            helper.initImage_status(R.id.xuedai_button3_iv,item.repay_status);

            //String.format("%.2f元", (moneny * 0.0275) + (moneny * 0.01)+moneny);
            String format = String.format("%.2f元", (item.money + item.service_fee + item.interest_money + item.weiyue_money));
            //应还金额 (本金+利息+服务费+违约金)
            helper.initText_hint(R.id.xuedai_button3_tv1,format);
            //已还金额
            helper.initText_hint(R.id.xuedai_button3_tv2,item.real_money+"元");
            //分期
            helper.initText_hint(R.id.xuedai_button3_tv3,item.current_flag+"/"+(loan_term/7)+"期");
            //已分期金额 只有在已分期为1 时候才显示
            TextView benjin = helper.initText_hint(R.id.xuedai_button3_tvbenjin, item.benjin + "元");
            benjin.setVisibility(View.INVISIBLE);

            //还款按钮

            //TextView textView1 = helper.initText(R.id.xuedai_button3_tv5, "");
            TextView textView1= helper.initText_huankuan(R.id.xuedai_button3_tv5,"",item.repay_status);
            if (frist ==position){
                textView1.setVisibility(View.VISIBLE);
                LogUtils.i("我显示有按钮"+position);
                //tag = false;
            }else{
                textView1.setVisibility(View.INVISIBLE);
                LogUtils.i("我显示没按钮"+position);
            }
            /**
             * 如果 已经分期过就显示 易分期金额
             */
            if (item.again_flag==1){
                benjin.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.INVISIBLE);
            }

            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击还款
                    mListViewCallBack.getRepayment(position);
                }
            });
            //计划还款日
            helper.initText_hint(R.id.xuedai_button3_tv4,item.plan_date);
            //再分期提示
          //  helper.initText(R.id.xuedai_button3_tv6,"再分期"+item.zaifenqi+"");
            //再分期按钮
            TextView textView = helper.initText(R.id.xuedai_button3_tv7, "");
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListViewCallBack.getZaifenqi(position);
                }
            });
            LogUtils.i("第一个"+frist);
            //重要的逻辑
            //以前没分过期 并且 可以分期
            if (item.again_flag==0&&item.zaifenqi==1){
                if (frist==position){
                    //是否显示下面
                    helper.initLinearLayout_Show(R.id.ll2,1);
                }else{
                    helper.initLinearLayout_Show(R.id.ll2,0);
                }

                // helper.initLinearLayout_Show(R.id.ll2,item.again_flag);

            }else{
                helper.initLinearLayout_Show(R.id.ll2,0);
            }
            //一个都没有按钮
            if (frist==-1){
                helper.initLinearLayout_Show(R.id.ll2,0);
            }





        }


    }


    @Override
    public void onLoad() {
        mListViewLoadMore.listViewLoadMore(type);
    }

}
