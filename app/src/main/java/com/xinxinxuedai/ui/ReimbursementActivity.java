package com.xinxinxuedai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.xinxinxuedai.MVP.ReimbursementActivity.ReimbursementActivity_C;
import com.xinxinxuedai.MVP.ReimbursementActivity.ReimbursementActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.view.MyListView;
import com.xinxinxuedai.view.dialog.DialogCallBack;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_2;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_3;

import java.net.HttpURLConnection;
import java.util.List;

//我要还款activity
public class ReimbursementActivity extends BaseActivity implements View.OnClickListener, ReimbursementActivity_C {
    HttpURLConnection httpURLConnection;
    private initAction_Bar mRelativeLayout_title;
    private TextView reimbursement_tv_hint;
    private TextView reimbursement_tv_logo;
    private XueDaiButton_2 reimbursement_xdb_1;
    private XueDaiButton_3 reimbursement_xdb_2;
    private XueDaiButton_3 reimbursement_xdb_3;
    private RelativeLayout reimbursement_rl;
    private initAction_Bar relativeLayout_title;
    private ReimbursementActivity_P mReimbursementActivity_p;
    private MyListView mReimbursement_lv;
    private XRefreshView xrefreshview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_reimbursement;
    }

    @Override
    public void initView() {
        mRelativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        mRelativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("我要还款");
            }
        });


        reimbursement_tv_hint = (TextView) findViewById(R.id.reimbursement_tv_hint);
        reimbursement_tv_hint.setOnClickListener(this);

        reimbursement_tv_logo = (TextView) findViewById(R.id.reimbursement_tv_logo);
        reimbursement_tv_logo.setOnClickListener(this);

       // reimbursement_xdb_1 = (XueDaiButton_2) findViewById(R.id.reimbursement_xdb_1);

        //reimbursement_xdb_1.setOnClickListener(this);

//        reimbursement_xdb_2 = (XueDaiButton_3) findViewById(R.id.reimbursement_xdb_2);
//        reimbursement_xdb_2.setOnClickListener(this);
//
//        reimbursement_xdb_3 = (XueDaiButton_3) findViewById(R.id.reimbursement_xdb_3);
//        reimbursement_xdb_3.setOnClickListener(this);

        reimbursement_rl = (RelativeLayout) findViewById(R.id.reimbursement_rl);
        reimbursement_rl.setOnClickListener(this);
        //listView
        mReimbursement_lv = (MyListView) findViewById(R.id.reimbursement_lv);
        //xrefreshview = (XRefreshView) findViewById(R.id.xrefreshview);

        initData();
    }

    @Override
    public void initP() {
        mReimbursementActivity_p =new  ReimbursementActivity_P(AppContext.getApplication());
        mReimbursementActivity_p.setCallBack(this);

    }

    @Override
    public void initData() {


        //获取网络添加列表数据
        mReimbursementActivity_p.initListViewData(mReimbursement_lv,1);

        //初始化 下拉刷新
       //mReimbursementActivity_p.initRefurbish(xrefreshview);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.reimbursement_xdb_1:
//                //mReimbursementActivity_p.topUp();
//                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=httpURLConnection){
            //断开连接
            httpURLConnection.disconnect();
        }
        dump();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (null!=mReimbursementActivity_p){

            mReimbursementActivity_p.initListViewData(mReimbursement_lv,2);
        }
    }

    /**
     * 获取网络请求
     *
     * @param httpURLConnection
     */
    @Override
    public void getNetRequest(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    /**
     * 是否还款
     * @param positon
     * @param data
     * @param again_flag
     */
    @Override
    public void getShowDialog1(final int positon, List<RepaymentList.DataBean> data, int again_flag) {
        RepaymentList.DataBean dataBean = data.get(positon);
        Intent intent = new Intent(AppContext.getApplication(), Repayment_detailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataList",dataBean);
        bundle.putInt("positon",positon);
        bundle.putInt("again_flag",again_flag);
        intent.putExtras(bundle);
        startActivityForResult(intent,0);
        LogUtils.i("进到还款详情的界面的数据"+dataBean+"多少号"+positon);

       // startActivity(new Intent(AppContext.getApplication(),Repayment_detailsActivity.class));

//        UtilsDialog.showDialog_Text(this, "还款", "是否还款", new DialogCallBack() {
//            @Override
//            public void confirm() {
//                LogUtils.i("我点了还款号码是"+positon);
//                mReimbursementActivity_p.subHuanKuan(positon);
//            }
//
//            @Override
//            public void cancel() {
//                LogUtils.i("取消还款");
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      LogUtils.i("后一个界面过来回来的数据是"+"requestCode"+requestCode+"\t"+"resultCode"+resultCode);
        switch (resultCode){
            //还款成功
            case 99:
                mReimbursementActivity_p.refurbish();
            break;


        }
    }

    @Override
    public void getShowDialog2(int positon, List<RepaymentList.DataBean> data) {
        RepaymentList.DataBean dataBean = data.get(positon);
        Intent intent = new Intent(AppContext.getApplication(), Re_stagingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("zaifenqi",dataBean);
        intent.putExtras(bundle);
        startActivity(intent);
       // startActivity(new Intent(AppContext.getApplication(),Re_stagingActivity.class));
    }

    /**
     *  提前还款的显示
     * @param positon
     */
    @Override
    public void getShowDialog3(double positon) {
        double money = positon;
        String info ="\t先息后本\n本金+所有应还的利息+当期的服务费\n\t等额本息(可再分期)\n本金+本金的*10% +所有利息+当期服务费\n本次需要支付的金额:";
        String msg = info+money+"";
        UtilsDialog.showDialog_Text(this, "提前还款", msg, new DialogCallBack() {
            @Override
            public void confirm() {
                mReimbursementActivity_p.subTiQianHuanKuan();
            }

            @Override
            public void cancel() {
                LogUtils.i("取消还款");
            }
        });
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {

    }

    /**
     * 清除的方法
     */
    @Override
    public void dump() {
         httpURLConnection = null;
          mRelativeLayout_title= null;
          reimbursement_tv_hint= null;
          reimbursement_tv_logo= null;
          reimbursement_xdb_1= null;
          reimbursement_xdb_2= null;
          reimbursement_xdb_3= null;
          reimbursement_rl= null;
          relativeLayout_title= null;
          mReimbursementActivity_p= null;
          mReimbursement_lv= null;
          xrefreshview= null;
    }
}
