package com.xinxinxuedai.MVP.RegisterActivity.BroadcastReceiver;

/**
 * Created by 35876 于萌萌
 * 创建日期: 13:54 . 2016年12月01日
 * 描述:倒计时的广播
 * <p>
 * <p>
 * 备注:
 */

//public class ReceivercountTime extends BroadcastReceiver {
//
//    TextView login_cunttime;
//    public ReceivercountTime() {
//    }
//
//    public ReceivercountTime(TextView login_cunttime) {
//        this.login_cunttime =login_cunttime;
//    }
//
//    @Override
//    public void onReceive(final Context context, Intent intent) {
//        String action = intent.getAction();
//        if ("countTime".equals(action)){
//            String count = intent.getStringExtra("count");
//            int i = Integer.parseInt(count);
//            if (i<1){
//                login_cunttime.setText("发送验证码");
//                login_cunttime.setEnabled(true);
//            }else{
//                login_cunttime.setText(count);
//                login_cunttime.setEnabled(false);
//            }
//        }
//
//    }
//}