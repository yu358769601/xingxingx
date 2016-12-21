package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.base.BaseActivity;

public class ShowInfoActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_show_info;
    }

    @Override
  public void initView() {
        TextView showinfo_sub = (TextView) findViewById(R.id.showinfo_sub);
        showinfo_sub.setOnClickListener(this);
    }

    @Override
    public void initP() {

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
            case R.id.showinfo_sub:
                finish();
            break;
        }
    }
}
