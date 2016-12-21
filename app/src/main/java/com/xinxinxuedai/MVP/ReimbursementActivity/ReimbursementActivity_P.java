package com.xinxinxuedai.MVP.ReimbursementActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.adapter.ListViewCallBack;
import com.xinxinxuedai.adapter.MyListView_04_more;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.bean.GetLoanDetail;
import com.xinxinxuedai.bean.RePayMent;
import com.xinxinxuedai.bean.UserLoanAdvanceMoney;
import com.xinxinxuedai.bean.huandaiItem;
import com.xinxinxuedai.request.GetLoanDetail_Request;
import com.xinxinxuedai.request.LoanAdvanceMoney_Request_Request;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.RepaymentListRequest;
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
    static ReimbursementActivity_P mReimbursementActivity_p;
    Context context;
    private MyListView_04_more mMyListView_04_more;
    MyListView reimbursement_lv;
    public ReimbursementActivity_P(Context context) {
        this.context = context;
    }

    public static ReimbursementActivity_P getReimbursementActivity_p(Context context){
        if (mReimbursementActivity_p==null){
            return mReimbursementActivity_p = new ReimbursementActivity_P(context);
        }

        return mReimbursementActivity_p;
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
                    XueDaiButton_2 xueDaiButton_2 = new XueDaiButton_2(context);
                    //0先息后本  1等额本息
                    //String status = getLoanDetail.loan_plan=="0"?"先息后本":"等额本息";
                    xueDaiButton_2
                            .setText_balance_of_account(getLoanDetail.money+"元")
                            .setText_borrow_money(getLoanDetail.money+"元")
                            .setText_return_the_principal(getLoanDetail.loan_plan=="0"?"先息后本":"等额本息")
                            .setText_rightbutton_fenqi(getLoanDetail.loan_term+"天");
                    //  loan_term
                    initListOnclic(xueDaiButton_2);
                    reimbursement_lv.addHeaderView(xueDaiButton_2);

                    //网络获取数据
                    sendRepaymentListRequest(getLoanDetail.loan_term);
                }
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }

    private void initData(MyListView reimbursement_lv, List<RePayMent.DataBean> items, int loan_term) {
        if (items==null||items.size()==0){
            return;
        }
        mMyListView_04_more = new MyListView_04_more(AppContext.getApplication(), 0, items,loan_term ,new ListViewCallBack() {
            @Override
            public void getRepayment(int positon) {
                LogUtils.i("我点了还款位置是"+positon);
                reimbursementActivity_c.getShowDialog1(positon);
            }

            @Override
            public void getZaifenqi(int positon) {
                LogUtils.i("我点了再分期位置是"+positon);
                reimbursementActivity_c.getShowDialog2(positon);
            }
        });

        reimbursement_lv.setAdapter(mMyListView_04_more);
        UtilsToast.showToast(context, "网络加载完成~");
    }

    private void initListOnclic(XueDaiButton_2 xueDaiButton_2) {
        xueDaiButton_2.setCallBack(new button_CallBack() {
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
                        mReimbursementActivity_p.topUp();
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
                        UtilsToast.showToast(AppContext.getApplication(), "点了提前结清");

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
        LoanAdvanceMoney_Request_Request.request(context, hashtable, new NetWorkCallBack<UserLoanAdvanceMoney>() {
            @Override
            public void onSucceed(UserLoanAdvanceMoney money, int dataMode) {
                int moneyNum = 100;
                reimbursementActivity_c.getShowDialog3(moneyNum);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }

    List<RePayMent.DataBean> data;
    @NonNull
    private List<RePayMent.DataBean> sendRepaymentListRequest(final int loan_term) {

        RepaymentListRequest.request(context, new NetWorkCallBack<RePayMent>() {


            @Override
            public void onSucceed(RePayMent payMent,int dataMode) {
                data =  payMent.data;
                //获取之后设置数据
                initData(reimbursement_lv, data,loan_term);
            }

            @Override
            public void onError(String jsonObject) {

            }

        });


        return data;
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
        RePayMent.DataBean dataBean = data.get(0);
        //借款id
        hashtable.put("user_id",dataBean.user_loan_id);
        LogUtils.i("id"+dataBean.user_loan_id);
        UserLoanAdvanceMoney_Request_Request.request(context, hashtable, new NetWorkCallBack<UserLoanAdvanceMoney>() {
            @Override
            public void onSucceed(UserLoanAdvanceMoney money, int dataMode) {

            }

            @Override
            public void onError(String jsonObject) {

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
