package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxinxuedai.MVP.OrganizingDataActivity.OrganizingDataActivity_C;
import com.xinxinxuedai.MVP.OrganizingDataActivity.OrganizingDataActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogCallBack;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

import java.util.ArrayList;
//完善信息activity
public class OrganizingDataActivity extends BaseActivity implements View.OnClickListener, OrganizingDataActivity_C {

    private TextView organizingdata_tv;
    private TextView organizingdata_tv1;
    private RelativeLayout organizingdata_rl;
    private initAction_Bar relativeLayout_title;
    private TextView organizingdata_tv2;
    private TextView organizingdata_tv3;
    private TextView organizingdata_hint_ad;
    private EditText organizingdata_ed;
    private TextView organizingdata_tv_sub;
    private OrganizingDataActivity_P mOrganizingDataActivity_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initP();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_organizing_data;
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
                textView.setText("完善资料");
            }
        });
        //选择借款用途
        organizingdata_tv1 = (TextView) findViewById(R.id.organizingdata_tv1);
        organizingdata_tv1.setOnClickListener(this);
        //选择申请额度
        organizingdata_tv2 = (TextView) findViewById(R.id.organizingdata_tv2);
        organizingdata_tv2.setOnClickListener(this);
        //选择申请期限
        organizingdata_tv3 = (TextView) findViewById(R.id.organizingdata_tv3);
        organizingdata_tv3.setOnClickListener(this);
        //你现在的住址
        organizingdata_hint_ad = (TextView) findViewById(R.id.organizingdata_hint_ad);
        organizingdata_hint_ad.setOnClickListener(this);
        //请输入您现住址精确到门牌号
        organizingdata_ed = (EditText) findViewById(R.id.organizingdata_ed);
        organizingdata_ed.setOnClickListener(this);
        //下一步
        organizingdata_tv_sub = (TextView) findViewById(R.id.organizingdata_tv_sub);
        organizingdata_tv_sub.setOnClickListener(this);

    }

    @Override
    public void initP() {
        mOrganizingDataActivity_p = OrganizingDataActivity_P.getmOrganizingDataActivity_p(this);
        mOrganizingDataActivity_p.setCallBack(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择借款用途
            case R.id.organizingdata_tv1:
                ArrayList<String> strings1 = new ArrayList<>();
                strings1.add("1买大力");
                strings1.add("1买小泰");
                strings1.add("1一动不动是王八");
                UtilsDialog.showDialog(this, "选择借款用途", strings1, new UtilsDialogCallBack() {
                    @Override
                    public void RadioGroupNum(int selectNum, String selectNumInfo) {
                        organizingdata_tv1.setText(selectNumInfo);
                    }
                });
            break;
            //选择申请额度
            case R.id.organizingdata_tv2:
                ArrayList<String> strings2 = new ArrayList<>();
                strings2.add("2买大力");
                strings2.add("2买小泰");
                strings2.add("2一动不动是王八");
                UtilsDialog.showDialog(this, "选择借款用途", strings2, new UtilsDialogCallBack() {
                    @Override
                    public void RadioGroupNum(int selectNum, String selectNumInfo) {
                        organizingdata_tv2.setText(selectNumInfo);
                    }
                });
            break;
            //选择申请期限
            case R.id.organizingdata_tv3:
                ArrayList<String> strings3 = new ArrayList<>();
                strings3.add("3买大力");
                strings3.add("3买小泰");
                strings3.add("3一动不动是王八");
                UtilsDialog.showDialog(this, "选择借款用途", strings3, new UtilsDialogCallBack() {
                    @Override
                    public void RadioGroupNum(int selectNum, String selectNumInfo) {
                        organizingdata_tv3.setText(selectNumInfo);
                    }
                });
            break;
            //提交
            case R.id.organizingdata_tv_sub:
                ArrayList<TextView> textViews = new ArrayList<>();
                organizingdata_tv1.setTag(1);
                textViews.add(organizingdata_tv1);
                organizingdata_tv2.setTag(2);
                textViews.add(organizingdata_tv2);
                organizingdata_tv3.setTag(3);
                textViews.add(organizingdata_tv3);
                mOrganizingDataActivity_p.getTextViews(textViews,organizingdata_ed);
            break;
        }

    }

    private void submit() {
        // validate
        String ed = organizingdata_ed.getText().toString().trim();
        if (TextUtils.isEmpty(ed)) {
            Toast.makeText(this, "请输入您现住址精确到门牌号", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
