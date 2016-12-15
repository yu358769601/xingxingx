package com.example.androidmobilepy36;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.fuiou.pay.FyPay;
import com.fuiou.pay.FyPayCallBack;
import com.fuiou.pay.util.AppConfig;
import com.fuiou.pay.util.MD5UtilString;

public class MainPayActivity extends Activity implements OnClickListener {
	private EditText MchNtCd, UserId, BankCard, Amt, MchntOrdId, IdType, IdNo,
			Name;
	private String orderNo;
	private Button pay;
	private String mMchnt_Key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";//测试
	private String mURL = "www.baidu.com";
	// private String mURL =
	// "http://www-1.fuiou.com:18670/mobile_pay/update/receiveSDK.pay";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		FyPay.setDev(false);
		FyPay.init(MainPayActivity.this);

	}
	private void initView() {
		MchNtCd = (EditText) findViewById(R.id.MchNtCd);
		UserId = (EditText) findViewById(R.id.UserId);
		BankCard = (EditText) findViewById(R.id.BankCard);
		Amt = (EditText) findViewById(R.id.Amt);
		MchntOrdId = (EditText) findViewById(R.id.MchntOrdId);
		IdType = (EditText) findViewById(R.id.IdType);
		IdNo = (EditText) findViewById(R.id.IdNo);
		Name = (EditText) findViewById(R.id.Name);
		pay = (Button) findViewById(R.id.pay);
		pay.setOnClickListener(this);

	}

	public void onClick(View v) {
		int i1 = v.getId();
		if (i1 == R.id.pay) {
			String Sing = MD5UtilString.MD5Encode("02" + "|" + "2.0" + "|"
					+ MchNtCd.getText().toString() + "|"
					+ MchntOrdId.getText().toString() + "|"
					+ UserId.getText().toString() + "|"
					+ Amt.getText().toString() + "|"
					+ BankCard.getText().toString() + "|" + "http://www-1.fuiou.com:18670/mobile_pay/update/receive.pay" + "|"
					+ Name.getText().toString() + "|"
					+ IdNo.getText().toString() + "|"
					+ IdType.getText().toString() + "|" + mMchnt_Key);
			Bundle bundle = new Bundle();
			bundle.putString(AppConfig.MCHNT_CD, MchNtCd.getText().toString());
			bundle.putString(AppConfig.MCHNT_AMT, Amt.getText().toString());
			bundle.putString(AppConfig.MCHNT_BACK_URL, "http://www-1.fuiou.com:18670/mobile_pay/update/receive.pay");
			bundle.putString(AppConfig.MCHNT_BANK_NUMBER, BankCard.getText().toString());
			bundle.putString(AppConfig.MCHNT_ORDER_ID, MchntOrdId.getText().toString());
			bundle.putString(AppConfig.MCHNT_USER_IDCARD_TYPE, IdType.getText().toString());
			bundle.putString(AppConfig.MCHNT_USER_ID, UserId.getText().toString());
			bundle.putString(AppConfig.MCHNT_USER_IDNU, IdNo.getText().toString());
			bundle.putString(AppConfig.MCHNT_USER_NAME, Name.getText().toString());
			bundle.putString(AppConfig.MCHNT_SING_KEY, Sing);
			bundle.putString(AppConfig.MCHNT_SDK_SIGNTP, "MD5");
			bundle.putString(AppConfig.MCHNT_SDK_TYPE, "02");
			bundle.putString(AppConfig.MCHNT_SDK_VERSION, "2.0");

			int i = FyPay.pay(MainPayActivity.this, bundle, new FyPayCallBack() {

				@Override
				public void onPayComplete(String arg0, String arg1, Bundle arg2) {
					// TODO Auto-generated method stub
					Log.e("fuiou", "----------rspCode:" + arg0.toString());
					Log.e("fuiou", "----------rspDesc:" + arg1.toString());
					Log.e("fuiou", "----------extraData:" + arg2.toString());

				}

				@Override
				public void onPayBackMessage(String arg0) {
					// TODO Auto-generated method stub
					Log.e("fuiou", "----------extraData:" + arg0.toString());
				}
			});

		} else {
		}

	}
}
