package com.xinxinxuedai.MVP.mainActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xinxinxuedai.view.xuedai_button.XueDaiButton;
import com.xinxinxuedai.yumengmeng.yumengmeng01.view.MyViewPger;

/**
 * Created by 35876 于萌萌
 * 创建日期: 11:24 . 2016年11月30日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface MainActivity_method {
    //获取设备尺寸
    void initGetWindow();
    //设置轮播图高度
    void initViewPager(MyViewPger myViewPger);
    //给ViewPager设置数据
    void initViewData(LinearLayout activity_01_ll , ImageView iv_red_point);
    //按钮被点击了
    void initClick(XueDaiButton xueDaiButton);
    //按钮被点击了
    void initClickView(View view);

}
