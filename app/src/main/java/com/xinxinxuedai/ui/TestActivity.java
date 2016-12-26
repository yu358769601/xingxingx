package com.xinxinxuedai.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.db.DAO.MyDbDAO;
import com.xinxinxuedai.db.DAO.dbCallBackHelper;
import com.xinxinxuedai.db.Table;
import com.xinxinxuedai.db.bean.JsonAll;

import java.util.ArrayList;

public class TestActivity extends Activity implements View.OnClickListener {

    private TextView test_getdata;
    private TextView test_data;
    private RelativeLayout activity_test;
    private TextView test_romev_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        test_getdata = (TextView) findViewById(R.id.test_getdata);
        test_getdata.setOnClickListener(this);
        test_data = (TextView) findViewById(R.id.test_data);
        test_data.setOnClickListener(this);
        test_romev_data = (TextView) findViewById(R.id.test_romev_data);
        test_romev_data.setOnClickListener(this);
        activity_test = (RelativeLayout) findViewById(R.id.activity_test);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_getdata:
                new MyDbDAO(AppContext.getApplication(),JsonAll.class).findAll(Table.MyJson,new dbCallBackHelper<JsonAll>(){
                    /**
                     * 获取所有的信息成功
                     *
                     * @param arrayList
                     */
                    @Override
                    public void getAllDataSuccess(ArrayList<JsonAll> arrayList) {
                        test_data.setText(arrayList.toString());
                    }
                });
            break;
            case R.id.test_romev_data:
                new MyDbDAO(AppContext.getApplication(),JsonAll.class).rmoveAll(Table.MyJson);
            break;
        }
    }
}
