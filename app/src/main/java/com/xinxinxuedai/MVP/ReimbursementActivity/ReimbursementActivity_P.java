package com.xinxinxuedai.MVP.ReimbursementActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.adapter.ListViewCallBack;
import com.xinxinxuedai.adapter.MyListView_04_more;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.GetLoanDetail;
import com.xinxinxuedai.bean.LoanAdvanceMoney;
import com.xinxinxuedai.bean.RePayMentend;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.bean.UserLoanAdvanceMoney;
import com.xinxinxuedai.bean.huandaiItem;
import com.xinxinxuedai.request.GetInfo_Request;
import com.xinxinxuedai.request.GetLoanDetail_Request;
import com.xinxinxuedai.request.LoanAdvanceMoney_Request_Request;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.RepaymentListRequest;
import com.xinxinxuedai.request.RepaymentListRequestZFQ;
import com.xinxinxuedai.request.Repayment_Request;
import com.xinxinxuedai.request.UserLoanAdvanceMoney_Request_Request;
import com.xinxinxuedai.ui.TopUpActivity;
import com.xinxinxuedai.view.MyListView;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_2;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:26 . 2016年12月07日
 * 描述:我要还款_P
 * <p>
 * <p>
 * 备注:
 */

public class ReimbursementActivity_P extends BaseMvp<ReimbursementActivity_C> implements ReimbursementActivity_M{
    Context context;
    private MyListView_04_more mMyListView_04_more;
    MyListView reimbursement_lv;
    private XueDaiButton_2 mXueDaiButton_2;

    public ReimbursementActivity_P(Context context) {
        this.context = context;
    }


    ReimbursementActivity_C reimbursementActivity_c;
    @Override
    public void setCallBack(ReimbursementActivity_C reimbursementActivity_c) {
        this.reimbursementActivity_c = reimbursementActivity_c;
    }


    /**
     * 点击了支付
     */
    @Override
    public void topUp() {
        Intent intent = new Intent(context, TopUpActivity. class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取我要还贷界面列表的数据
     * @param reimbursement_lv
     */
    @Override
    public void initListViewData(MyListView reimbursement_lv) {
        this.reimbursement_lv = reimbursement_lv;
        UtilsToast.showToast(context, "获取网络数据中~");
        //获取上面的 那个数据
        getTopData();




    }

    private void getTopData() {
        GetLoanDetail_Request.request(context, new NetWorkCallBack<GetLoanDetail>() {
            @Override
            public void onSucceed(GetLoanDetail getLoanDetail, int dataMode) {
                if (dataMode==NetWorkCallBack.NETDATA){
                    mXueDaiButton_2 = new XueDaiButton_2(context);
                    //0先息后本  1等额本息
                    //String status = getLoanDetail.loan_plan=="0"?"先息后本":"等额本息";
                    mXueDaiButton_2
                           // .setText_balance_of_account(getLoanDetail.money+"元")
                            .setText_borrow_money(getLoanDetail.money+"元")
                            .setText_return_the_principal(getLoanDetail.loan_plan=="0"?"先息后本":"等额本息")
                            .setText_rightbutton_fenqi(getLoanDetail.loan_term+"天");
                    //  loan_term
                    initListOnclic(mXueDaiButton_2);
                    reimbursement_lv.addHeaderView(mXueDaiButton_2);
                    getMoney(mXueDaiButton_2,getLoanDetail);

                }
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }

    private void getMoney(final XueDaiButton_2 xueDaiButton_2, final GetLoanDetail getLoanDetail) {
        GetInfo_Request.request(context, new NetWorkCallBack<GetInfo>() {
            @Override
            public void onSucceed(GetInfo getInfo, int dataMode) {
                xueDaiButton_2
                .setText_balance_of_account(getInfo.loan_money+"元");


                //网络获取数据
                sendRepaymentListRequest(getLoanDetail.loan_term);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }
    MyListView mReimbursement_lv;
    private void initData(MyListView reimbursement_lv, List<RepaymentList.DataBean> items, int loan_term) {
        boolean tag = false;
        if (items==null||items.size()==0){
            return;
        }
        int count = 0;
        //判断是否有逾期什么的 没有 就可以提前还款
        for (int i = 0; i < items.size(); i++) {
            RepaymentList.DataBean dataBean = items.get(i);
            switch (dataBean.repay_status){
            //    0 待还款  1 已还款 2 逾期 3提前还款 4坏账5减免
                case 1:
                    count++;
                    break;

                case 2:
                case 4:
                    tag =true;
                break;
            }
        }

        if (tag){
            //说明有不合适的数据
            LogUtils.i("有逾期可以不能显示提前结清");
            xueDaiButton_2.setText_tiqian_status(false);
        }else{
            LogUtils.i("没有逾期可以显示 提前结清");
            xueDaiButton_2.setText_tiqian_status(true);
        }
        if (count==items.size()){
            LogUtils.i("全都结款完毕");
            xueDaiButton_2.setText_tiqian_status(false);
        }

       //如果是 最后一期 并且 还是未还款 在做后一期 然后我就隐藏按钮
        SetLast(data);

        //设置列表数据
        mMyListView_04_more = new MyListView_04_more(AppContext.getApplication(), 0, items,loan_term ,new ListViewCallBack() {
            @Override
            public void getRepayment(int positon) {
                LogUtils.i("我点了还款位置是"+positon);
                reimbursementActivity_c.getShowDialog1(positon,data);
            }

            @Override
            public void getZaifenqi(int positon) {
                LogUtils.i("我点了再分期位置是"+positon);
                reimbursementActivity_c.getShowDialog2(positon,data);
            }
        });
        this.mReimbursement_lv = reimbursement_lv;
        mReimbursement_lv.setAdapter(mMyListView_04_more);
        UtilsToast.showToast(context, "网络加载完成~");
    }

    /**
     * 设置最后的显示问题
     * @param data
     */
    private void SetLast(List<RepaymentList.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            RepaymentList.DataBean dataBean = data.get(i);
            //如果都是 已结清的 只有最后一个 是没结清的 不显示上面的
            if (1==dataBean.repay_status){
                if (i==data.size()-1){
                    xueDaiButton_2.setText_tiqian_status(false);
                    break;
                }else{
                    xueDaiButton_2.setText_tiqian_status(true);
                }
            }
        }
    }

    XueDaiButton_2 xueDaiButton_2;
    private void initListOnclic(XueDaiButton_2 xueDaiButton_2) {
        this.xueDaiButton_2 = xueDaiButton_2;
        this.xueDaiButton_2.setCallBack(new button_CallBack() {
            @Override
            public void button_Click(View v) {
                // UtilsToast.showToast(AppContext.getApplication(),"充值");
                switch ((int) v.getTag()) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        UtilsToast.showToast(AppContext.getApplication(), "充值");
                        topUp();
                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:
                        UtilsToast.showToast(AppContext.getApplication(), "点到了左面");
                        break;
                    case 7:
                        UtilsToast.showToast(AppContext.getApplication(), "点到了右面");
                        break;
                    case 10:
                        //UtilsToast.showToast(AppContext.getApplication(), "点了提前结清");

                        getShowInfo();


                        break;
                }
            }
        });
    }

    /**
     * 获取一次性结清的 钱数
     */
    private void getShowInfo() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        //列表集合里面 有几个还款
        int huankuan=0;
        for (int i = 0; i < data.size(); i++) {
            RepaymentList.DataBean dataBean1 = data.get(i);
            if (0==dataBean1.repay_status){
                huankuan = dataBean1.id;

                break;
            }
        }
        //还到第几期的是点的 提前还款
        hashtable.put("id", huankuan+"");
        LoanAdvanceMoney_Request_Request.request(context, hashtable, new NetWorkCallBack<LoanAdvanceMoney>() {
            @Override
            public void onSucceed(LoanAdvanceMoney money, int dataMode) {
                double moneyNum = money.data;
                reimbursementActivity_c.getShowDialog3(moneyNum);
            }

            @Override
            public void onError(String jsonObject) {
                UtilsToast.showToast(context, jsonObject);
            }
        });
    }

    List<RepaymentList.DataBean> data;
    //获取列表数据
    @NonNull
    private void sendRepaymentListRequest(final int loan_term) {
//        //如果没有再分期
//        if (0==Share.getInt(context, "again_flag")){
//            RepaymentListRequest.request(context, new NetWorkCallBack<RepaymentList>() {
//
//
//                @Override
//                public void onSucceed(RepaymentList payMent, int dataMode) {
//                    data =  payMent.data;
//                    //获取之后设置数据
//                    initData(reimbursement_lv, data,loan_term);
//                }
//
//                @Override
//                public void onError(String jsonObject) {
//
//                }
//
//            });
//        }else{
//            //有再分期
            RepaymentListRequestZFQ.request(context, new NetWorkCallBack<RepaymentList>() {
                @Override
                public void onSucceed(RepaymentList repaymentList, int dataMode) {
                    data =  repaymentList.data;

                    //获取之后设置数据
                    initData(reimbursement_lv, data,loan_term);
                }

                @Override
                public void onError(String jsonObject) {

                }
            });

//
//

    }



    @NonNull
    private ArrayList<huandaiItem> sendRepaymentListRequestTo() {
        RepaymentListRequest.requestmore(context, new NetWorkCallBack() {

            @Override
            public void onSucceed(Object o,int dataMode) {

            }

            @Override
            public void onError(String jsonObject) {

            }
        });

        ArrayList<huandaiItem> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            items.add(new huandaiItem("20"+i,"666"+i,"2018年2月38日"+i,"200天"+i));
        }
        return items;
    }

    /**
     * 初始化刷新
     *
     * @param xRefreshView
     */
    @Override
    public void initRefurbish(final XRefreshView xRefreshView) {
        configXRfreshView(xRefreshView, new XRefreshView.SimpleXRefreshListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                LogUtils.i("刷新_下拉刷新");
                //mMyListView_04_more.setData(sendRepaymentListRequest());
                xRefreshView.stopRefresh();
            }
            //上拉加载更多
            @Override
            public void onLoadMore(boolean isSilence) {
                LogUtils.i("调用加载更多");
                //加载更多
              //  mMyListView_04_more.addData(sendRepaymentListRequestTo());
                xRefreshView.stopLoadMore();
            }
        });

    }

    /**
     * 提前还款访问网络
     */
    @Override
    public void subTiQianHuanKuan() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        if (null==data||data.size()==0){
            return;
        }
        RepaymentList.DataBean dataBean = data.get(0);
        //列表集合里面 有几个还款
        int huankuan=0;
        for (int i = 0; i < data.size(); i++) {
            RepaymentList.DataBean dataBean1 = data.get(i);
            if (0==dataBean1.repay_status){
                huankuan = dataBean1.id;
                break;
            }
        }
        //借款id
        hashtable.put("loan_id",dataBean.user_loan_id);
        //还到第几期的是点的 提前还款
        hashtable.put("id", huankuan+"");

        LogUtils.i("loan_id"+dataBean.user_loan_id);


        UserLoanAdvanceMoney_Request_Request.request(context, hashtable, new NetWorkCallBack<UserLoanAdvanceMoney>() {
            @Override
            public void onSucceed(UserLoanAdvanceMoney money, int dataMode) {
                UtilsToast.showToast(context, money.message);
                LogUtils.i("提前还款网络成功回来的数据"+money.message);
            }

            @Override
            public void onError(String jsonObject) {
               // LogUtils.i("最外层我在这里"+jsonObject);
                UtilsToast.showToast(context, jsonObject);
            }
        });
    }

    /**item 上面的还款
     * @param postion
     */
    @Override
    public void subHuanKuan(final int postion) {
        if (null==data||data.size()==0){
            return;
        }
        RepaymentList.DataBean dataBean = data.get(postion);
        int id = dataBean.id;
        String user_loan_id = dataBean.user_loan_id;
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        hashtable.put("id",id+"");
        hashtable.put("loan_id",user_loan_id);



        Repayment_Request.request(context, hashtable, new NetWorkCallBack<RePayMentend>() {
            @Override
            public void onSucceed(RePayMentend rePayMent1, int dataMode) {
                UtilsToast.showToast(context, rePayMent1.message);
                LogUtils.i("还款网络成功回来的数据"+ rePayMent1.message);
                //reimbursement_lv.removeAllViews();
                data.clear();
                reimbursement_lv.removeHeaderView(mXueDaiButton_2);
                mMyListView_04_more.notifyDataSetChanged();
                //重新获取一下网络刷新一下列表
                initListViewData(reimbursement_lv);

            }

            @Override
            public void onError(String jsonObject) {
                UtilsToast.showToast(context, jsonObject);
//                data.remove(postion);
//                mMyListView_04_more.notifyDataSetChanged();
            }
        });
    }


    private void configXRfreshView(XRefreshView xRefreshView, XRefreshView.SimpleXRefreshListener listener) {
        xRefreshView.setPullLoadEnable(true);
        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setAutoLoadMore(false);
        //两种方式设置空布局，传入空布局的view或者传入布局id都可以
//        TextView textView = new TextView(this);
//        textView.setText("没有数据，点击刷新");
//        textView.setGravity(Gravity.CENTER);
//        xRefreshView.setEmptyView(textView);
//        xRefreshView.setEmptyView(R.layout.layout_emptyview);
        xRefreshView.setXRefreshViewListener(listener);
    }



}
