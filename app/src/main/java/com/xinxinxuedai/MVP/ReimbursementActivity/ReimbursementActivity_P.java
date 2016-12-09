package com.xinxinxuedai.MVP.ReimbursementActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.andview.refreshview.XRefreshView;
import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.adapter.MyListView_04_more;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.bean.huandaiItem;
import com.xinxinxuedai.request.RepaymentListRequest;
import com.xinxinxuedai.ui.TopUpActivity;
import com.xinxinxuedai.view.MyListView;

import java.net.HttpURLConnection;
import java.util.ArrayList;

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
        //网络获取数据
        ArrayList<huandaiItem> items = sendRepaymentListRequest();
        //获取之后设置数据
        initData(reimbursement_lv, items);



    }

    private void initData(MyListView reimbursement_lv, ArrayList<huandaiItem> items) {
        if (items==null||items.size()==0){
            return;
        }
        mMyListView_04_more = new MyListView_04_more(AppContext.getApplication(), 0, items);
        reimbursement_lv.setAdapter(mMyListView_04_more);
        UtilsToast.showToast(context, "网络加载完成~");
    }

    @NonNull
    private ArrayList<huandaiItem> sendRepaymentListRequest() {
        RepaymentListRequest.request(context, new NetAesCallBack() {
            @Override
            public void onSucceed(JSONObject jsonObject) {

            }

            @Override
            public void onError(String errorString) {

            }

            @Override
            public void onBackHttpURLConnection(HttpURLConnection httpURLConnection) {
                reimbursementActivity_c.getNetRequest(httpURLConnection);
            }
        });


        ArrayList<huandaiItem> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            items.add(new huandaiItem("20"+i,"666"+i,"2018年2月38日"+i,"200天"+i));
        }

        return items;
    }
    @NonNull
    private ArrayList<huandaiItem> sendRepaymentListRequestTo() {
        RepaymentListRequest.requestmore(context, new NetAesCallBack() {
            @Override
            public void onSucceed(JSONObject jsonObject) {

            }

            @Override
            public void onError(String errorString) {

            }

            @Override
            public void onBackHttpURLConnection(HttpURLConnection httpURLConnection) {
                reimbursementActivity_c.getNetRequest(httpURLConnection);
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
                mMyListView_04_more.setData(sendRepaymentListRequest());
                xRefreshView.stopRefresh();
            }
            //上拉加载更多
            @Override
            public void onLoadMore(boolean isSilence) {
                LogUtils.i("调用加载更多");
                //加载更多
                mMyListView_04_more.addData(sendRepaymentListRequestTo());
                xRefreshView.stopLoadMore();
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
        //xRefreshView.setEmptyView(R.layout.layout_emptyview);
        xRefreshView.setXRefreshViewListener(listener);
    }



}
