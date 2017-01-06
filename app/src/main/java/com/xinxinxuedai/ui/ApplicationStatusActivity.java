package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.ApplicationStatusActivity.ApplicationStatusActivity_C;
import com.xinxinxuedai.MVP.ApplicationStatusActivity.ApplicationStatusActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsTimer;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetLoanDetail;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_6;

import java.util.ArrayList;

//申请状态activity 借款状态
public class ApplicationStatusActivity extends BaseActivity implements ApplicationStatusActivity_C {

    private initAction_Bar relativeLayout_title;
    private TextView applicationstatus_tv1;
    private TextView applicationstatus_tv2;
    private TextView applicationstatus_tv3;
    private TextView applicationstatus_tv4;
    private TextView applicationstatus_tv5;
    private ApplicationStatusActivity_P mApplicationStatusActivity_p;
    private GetLoanDetail mHomeData;
    private LinearLayout mZaifenqi_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBox();
        initP();
        initView();
    }

    private void getBox() {
        Bundle extras = getIntent().getExtras();
        mHomeData = (GetLoanDetail) extras.getSerializable("HomeData");

    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_application_status;
    }

    @Override
    public void initView() {

        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("申请状态");
            }
        });


        applicationstatus_tv1 = (TextView) findViewById(R.id.applicationstatus_tv1);
        applicationstatus_tv2 = (TextView) findViewById(R.id.applicationstatus_tv2);
        applicationstatus_tv3 = (TextView) findViewById(R.id.applicationstatus_tv3);
        applicationstatus_tv4 = (TextView) findViewById(R.id.applicationstatus_tv4);
        applicationstatus_tv5 = (TextView) findViewById(R.id.applicationstatus_tv5);
        //展现再分期数据的
        mZaifenqi_ll = (LinearLayout) findViewById(R.id.zaifenqi_ll);
        initData();
    }

    @Override
    public void initP() {
        mApplicationStatusActivity_p =new  ApplicationStatusActivity_P(AppContext.getApplication());
        mApplicationStatusActivity_p.setCallBack(this);

    }

    @Override
    public void initData() {

        if (null!=mHomeData){
            LogUtils.i("主页有下级页面的数据"+mHomeData);
            setData(mHomeData);
        }
        //获取网络数据
        mApplicationStatusActivity_p.getData();
    }

    @Override
    public void initListener() {

    }


    /**
     * 给V的 数据设置
     *
     * @param data
     */
    @Override
    public void setData(GetLoanDetail data) {
        LogUtils.i("获取到借款详情"+data);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("资料未完善");
        strings.add("借款审核中");// 不能进
        strings.add("借款审核已通过，请在24小时之内关注银行卡资金是否到账。"); //不能进
        strings.add("借款审核未通过");
        strings.add("借款放款成功,请按照还款计划及时归还借款。");//不能    //能进我要还款
        strings.add("借款放款失败");
        strings.add("借款还款已完成");//能进
        strings.add("借款已提前还款");

        //0 先息后本  1 等额本息
        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("先息后本");
        strings1.add("等额本息");

        String applicationstatus_txt1 = applicationstatus_tv1.getHint().toString().trim();
        String applicationstatus_txt2 = applicationstatus_tv2.getHint().toString().trim();
        String applicationstatus_txt3 = applicationstatus_tv3.getHint().toString().trim();
        String applicationstatus_txt4 = applicationstatus_tv4.getHint().toString().trim();
        String applicationstatus_txt5 = applicationstatus_tv5.getHint().toString().trim();
        for (int i = 0; i <strings1.size() ; i++) {
            String s = strings1.get(i);

            if (Integer.parseInt(data.loan_plan)==i){
                //借款计划
                applicationstatus_tv1.setText(applicationstatus_txt1+s);
                break;

            }
        }
        //借款期限
        applicationstatus_tv2.setText(applicationstatus_txt2+data.loan_term+"天");
        //借款金额
        applicationstatus_tv3.setText(applicationstatus_txt3+data.money+"元");
        //申请时间
        applicationstatus_tv4.setText(applicationstatus_txt4+data.add_time);
        for (int i = 0; i <strings.size() ; i++) {
            String s = strings.get(i);
            if (data.loan_status==i){
                //借款状态
                applicationstatus_tv5.setText(applicationstatus_txt5+s);
                break;
            }
        }

        if (data.again_flag!=0){
            if (data.again.size()>0){
                mZaifenqi_ll.removeAllViews();
                for (int i = 0; i < data.again.size(); i++) {
                    GetLoanDetail.AgainBean againBean = data.again.get(i);
                    XueDaiButton_6 xueDaiButton_6 = setZFQData(againBean);
                    mZaifenqi_ll.addView(xueDaiButton_6);
                }
            }

        }

    }

    /**
     * 再分期的数据  新加的
     * @param againBean
     * @return
     */
    public XueDaiButton_6 setZFQData(GetLoanDetail.AgainBean againBean){
        LogUtils.i("在分期数据"+againBean);
        XueDaiButton_6 xueDaiButton_6 = new XueDaiButton_6(AppContext.getApplication());
        //分期期限
         // again_term : 56
//        * 放款时间
//                * again_fangkuan : 1483514223
//        * 申请时间
//                * again_time : 1483514141
//        * 服务费
//                * again_fuwu : 22.00
//        * 审核状态 0 待审核 1 审核通过 2 满标放款成功
//                * again_flag : 2
//        *分期金额
//                * again_money : 267.00
//        * 利息
//                * again_lixi : 8.00
//                *
//        * 手续费
//                * again_shouxufei : 26.70
        xueDaiButton_6
                .setTv1_Text(againBean.again_flag)
                .setTv2_Text(againBean.again_money+"")
                .setTv3_Text(againBean.again_term)
                .setTv4_Text(againBean.again_shouxufei)
              //  .setTv5_Text(againBean.again_fuwu)
                .setTv6_Text(UtilsTimer.getFormat(againBean.again_time*1000))
                .setTv7_Text(UtilsTimer.getFormat(againBean.again_fangkuan*1000));
        LogUtils.i("申请时间"+UtilsTimer.getFormat(againBean.again_time*1000)+"放款时间"+UtilsTimer.getFormat(againBean.again_fangkuan*1000));
        return xueDaiButton_6;
    }

    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dump();
    }

    /**
     * 清除的方法
     */
    @Override
    public void dump() {
          relativeLayout_title =null;
          applicationstatus_tv1=null;
          applicationstatus_tv2=null;
          applicationstatus_tv3=null;
          applicationstatus_tv4=null;
          applicationstatus_tv5=null;
          mApplicationStatusActivity_p=null;
          mHomeData=null;
    }
}
