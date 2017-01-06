package com.xinxinxuedai.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loveplusplus.update.AppUtils;
import com.xinxinxuedai.MVP.mainActivity.MainActivity_CallBack;
import com.xinxinxuedai.MVP.mainActivity.MainActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.UpDataApp;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.UpDataApp_Request;
import com.xinxinxuedai.view.dialog.DialogCallBack;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;
import com.xinxinxuedai.yumengmeng.yumengmeng01.view.MyViewPger;

import java.util.Hashtable;

//首页activity 主页
public class MainActivity extends BaseActivity implements View.OnClickListener, MainActivity_CallBack {

    private initAction_Bar mActivity_title;
    private XueDaiButton mTv1;
    private XueDaiButton mTv2;
    private XueDaiButton mTv3;
    private XueDaiButton mTv4;

    private MyViewPger mMain_vp;
    private ImageView iv_red_point;
    private MainActivity_P mMainActivity_p;
    private LinearLayout mActivity_01_ll;
    private ImageView mMain_iv;
    private TextView mMain_tv_name;
    private TextView mMain_tv_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
        initUpData();
    }

    private void initUpData() {
        //软件更新检查

//		//原来自动更新
//		UpdateChecker.checkForDialog(MainActivity.this, new CallBack() {
//			@Override
//			public void callDownLoad() {
//				UtilsToast.showToast(MainActivity.this, "正在后台下载更新");
//			}
//
//			@Override
//			public void callCancel(int apkforceUpData) {
//					if (apkforceUpData==0){
//						UtilsToast.showToast(MainActivity.this, "此次是强制更新取消退出软件");
//						finish();
//					}
//			}
//		});
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        UpDataApp_Request.request(AppContext.getApplication(), hashtable, new NetWorkCallBack<UpDataApp>() {
            @Override
            public void onSucceed(final UpDataApp upDataApp, int dataMode) {
                final String downloadUrl = upDataApp.data.wangzhi;
                int versionCode = AppUtils.getVersionCode(AppContext.getApplication());
                Integer versionCodeNew = upDataApp.data.banben;
                LogUtils.i("更新数据" + upDataApp + "本地版本号" + versionCode + "网络版本号" + versionCodeNew);
                //网络版本号和本地版本号
                if (versionCodeNew > versionCode) {
                    LogUtils.i("提示版升级");
                    String qiangzhi;
                    if (upDataApp.data.qiangzhi==1){
                         qiangzhi = upDataApp.data.msg+"ps.此版本为强制更新,如不更新将无法使用";
                    }else{
                        //qiangzhi ="啊实打实大十大大神大神大神DOI爱神的箭哦啊接DOI啊速度及哦啊的骄傲is基地哦我骄傲is的骄傲is的骚IDu我去玩一U盾哦和啊U盾哦啊是京东爱 奥斯倒是点击";
                        qiangzhi = upDataApp.data.msg;
                   }

                    UtilsDialog.showDialog_Text(MainActivity.this, "更新提示", qiangzhi, new DialogCallBack() {
                        @Override
                        public void confirm() {
                            UtilsToast.showToast(AppContext.getApplication(), "正在后台下载更新");
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(downloadUrl);
                           // Uri content_url = Uri.parse("http://a.wdjcdn.com/release/files/phoenix/5.24.2.12070/wandoujia-wandoujia-web_seo_baidu_binded_history_5.24.2.12070.apk?remove=2&append=%07%03eyJhcHBEb3dubG9hZCI6eyJkb3dubG9hZFR5cGUiOiJkb3dubG9hZF9ieV91cmwiLCJwYWNrYWdlTmFtZSI6ImNvbS5zcy5hbmRyb2lkLmFydGljbGUubmV3cyIsImRvd25sb2FkVXJsIjoiaHR0cDovL2FwcHMud2FuZG91amlhLmNvbS9yZWRpcmVjdD9zaWduYXR1cmVcdTAwM2QzYjFhZjExXHUwMDI2dXJsXHUwMDNkaHR0cCUzQSUyRiUyRmFway53YW5kb3VqaWEuY29tJTJGYyUyRmQ3JTJGMDRlNTU0ZDM5NDc3NGUwMDRmMzVmZjkxNjMzZWVkN2MuYXBrXHUwMDI2cG5cdTAwM2Rjb20uc3MuYW5kcm9pZC5hcnRpY2xlLm5ld3NcdTAwMjZtZDVcdTAwM2QwNGU1NTRkMzk0Nzc0ZTAwNGYzNWZmOTE2MzNlZWQ3Y1x1MDAyNmFwa2lkXHUwMDNkMjAzODEyNjZcdTAwMjZ2Y1x1MDAzZDU5NVx1MDAyNnNpemVcdTAwM2QxMjk4ODgyOFx1MDAyNnBvc1x1MDAzZHQlMkZoaXN0b3J5JTJGdmVyc2lvbnMiLCJ0aXRsZSI6IuS7iuaXpeWktOadoSIsImljb25VcmwiOiJodHRwOi8vaW1nLndkamltZy5jb20vbW1zL2ljb24vdjEvMS9hNi81MzQ1ZTgyNjY4MDg0NjI0NDQ3ZjNhMTQ1Y2E2NmE2MV83OF83OC5wbmcifX0Wdj01B0002f79796");
                            intent.setData(content_url);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void cancel() {
                            if (upDataApp.data.qiangzhi==1){
                                LogUtils.i("强制更新");
                                finish();
                            }

                        }
                    });

                }
            }
            @Override
            public void onError(String jsonObject) {

            }
        });


    }


    @Override
    public int getlayouXML() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mActivity_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        mActivity_title.setBack(false);
        mActivity_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("主页");
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(AppContext.getApplication(), TopUpActivity. class);
//                        startActivity(intent);
//                    }
//                });

//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(AppContext.getApplication(),Test2Activity.class));
//                    }
//                });
            }
        });



        mTv1 = (XueDaiButton) findViewById(R.id.tv1);
        mTv2 = (XueDaiButton) findViewById(R.id.tv2);
        mTv3 = (XueDaiButton) findViewById(R.id.tv3);
        mTv4 = (XueDaiButton) findViewById(R.id.tv4);

        TextView left= (TextView) findViewById(R.id.tv_left);
        left.setOnClickListener(this);

        //看数据库的(测试)
        TextView test = (TextView) findViewById(R.id.tv_test);
        test.setOnClickListener(this);
        //签到
        TextView tv_qiandao = (TextView) findViewById(R.id.tv_qiandao);
        tv_qiandao.setOnClickListener(this);
        //服务和借款
        TextView fuwu = (TextView) findViewById(R.id.fuwu);
        fuwu.setVisibility(View.GONE);
        fuwu.setOnClickListener(this);


        mMain_iv = (ImageView) findViewById(R.id.main_iv);
        mMain_iv.setOnClickListener(this);
        mMain_tv_name = (TextView) findViewById(R.id.main_tv_name);
        mMain_tv_name.setOnClickListener(this);


        mMain_vp = (MyViewPger) findViewById(R.id.main_vp);

        iv_red_point = (ImageView) findViewById(R.id.activity_01_point_red);
        iv_red_point.setVisibility(View.GONE);
        mActivity_01_ll = (LinearLayout) findViewById(R.id.activity_01_ll);
        //点击了登陆
        mMain_tv_money = (TextView) findViewById(R.id.main_tv_money);
        mMain_tv_money.setOnClickListener(this);

        initData();
        initListener();
        initText();
    }

    private long firstPressTime = 0;
    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        if((now - firstPressTime) > 2000)
        {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstPressTime = now;
        }else
        {
            finish();
            System.exit(0);
        }
    }

    private void initText() {
        mTv1.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv1被点击了");
                mTv1.setType(1);
                mMainActivity_p.initClick(mTv1);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv1))
                .setTextSmallColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setText("我要借款")
                .setTextSmall("Loan")
                .setInfoStuaus(true)
                .setInfoText("多种借款")
        ;

        mTv2.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv2被点击了");
                mTv2.setType(2);
                mMainActivity_p.initClick(mTv2);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv2))
                .setTextSmallColor(getResources().getColor(R.color.home_tv2))
                .setTopDrawable(R.drawable.home_tv02)
                .setText("我要还款")
                .setTextSmall("Repayment")
                .setInfoStuaus(true)
                .setInfoText("管理您的欠款")
        ;

        mTv3.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv3被点击了");
                mTv3.setType(3);
                mMainActivity_p.initClick(mTv3);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv3))
                .setTextSmallColor(getResources().getColor(R.color.home_tv3))
                .setTopDrawable(R.drawable.home_tv03)
                .setText("借款状态")
                .setTextSmall("State")
                .setInfoStuaus(true)
                .setInfoText("申请状态查询")
        ;

        mTv4.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv4被点击了");
                mTv4.setType(4);
                mMainActivity_p.initClick(mTv4);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv4))
                .setTextSmallColor(getResources().getColor(R.color.home_tv4))
                .setTopDrawable(R.drawable.home_tv04)
                .setText("关于我们")
                .setTextSmall("help")
                .setInfoStuaus(true)
                .setInfoText("帮助客服")
        ;




    }

    @Override
    public void initP() {


        mMainActivity_p = new MainActivity_P(this);
        mMainActivity_p.setCallBack(this);
    }


    @Override
    public void initData() {




        //获取当前设备的 高度宽度
        mMainActivity_p.initGetWindow();
        //给ViewPager 设置高度
        mMainActivity_p.initViewPager(mMain_vp);
        //给ViewPager 设置数据 并且画小红点 小白点
        mMainActivity_p.initViewData(mActivity_01_ll,iv_red_point);

        mMainActivity_p.getCallBackData();


    }


    @Override
    public void initListener() {

//        mTv1.setOnClickListener(this);
//        mTv2.setOnClickListener(this);
//        mTv3.setOnClickListener(this);
//        mTv4.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dump();
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  Share.saveToken(AppContext.getApplication(),"2");
        if (!Share.checkLogin(AppContext.getApplication())){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //如果是登录状态 我就查询钱数
        if (Share.checkLogin(AppContext.getApplication())){
            mMainActivity_p.getCallBackData();
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击了注册
            case R.id.main_iv:
                v.setTag(1);
                mMainActivity_p.initClickView(v);
            break;
            //点击了重置
            case R.id.main_tv_name:
                v.setTag(2);
                mMainActivity_p.initClickView(v);
            break;
            //点击了登陆
            case R.id.main_tv_money:
                v.setTag(3);
                mMainActivity_p.initClickView(v);
            break;
            case R.id.tv_test:
             startActivity(new Intent(AppContext.getApplication(),TestActivity.class));

            break;
            case R.id.tv_qiandao:
             startActivity(new Intent(AppContext.getApplication(),SigninActivity.class));

            break;
            case R.id.fuwu:
                LogUtils.i("我要打开");
                Intent intent = new Intent(AppContext.getApplication(), Fuwu_and_xieyi_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("classTag",1);
                intent.putExtras(bundle);
                startActivity(intent);

            break;
            case R.id.tv_left:
                startActivity(new Intent(AppContext.getApplication(),Re_stagingActivity.class));

            break;

        }
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
        mMainActivity_p = null;
      mActivity_title= null;
        mTv1 = null;
        mTv2= null;
        mTv3= null;
        mTv4= null;
        mMain_vp= null;
       iv_red_point= null;
        mMainActivity_p= null;
        mActivity_01_ll= null;
        mMain_iv= null;
        mMain_tv_name= null;
        mMain_tv_money= null;

    }

    /**
     * 左下角数据
     *
     * @param dataMoney
     */
    @Override
    public void setDataMoney(GetInfo dataMoney) {
        //设置名字
        mMain_tv_name.setText(mMain_tv_name.getHint()+dataMoney.loan_realname);
        String s = dataMoney.loan_money + "元";
        //设置余额
        mMain_tv_money.setText(mMain_tv_money.getHint()+s);
    }
}
