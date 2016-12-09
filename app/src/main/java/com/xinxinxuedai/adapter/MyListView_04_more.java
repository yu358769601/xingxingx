package com.xinxinxuedai.adapter;

import android.content.Context;

import com.xinxinxuedai.R;
import com.xinxinxuedai.base.BaseListViewAdapter_04;
import com.xinxinxuedai.bean.huandaiItem;
import com.xinxinxuedai.view.MyListView;

import java.util.ArrayList;


/**
 * Created by 35876 于萌萌
 * 创建日期: 16:00 . 2016年10月08日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */ //我的行程adapter
public class MyListView_04_more extends BaseListViewAdapter_04<huandaiItem> implements MyListView.OnLoadListener {
    public String net = "http://app.beta.9ac.com.cn/";
    ListViewLoadMore mListViewLoadMore;
    int type;

    public MyListView_04_more(Context context, int type, ArrayList datas) {
        super(context, type, datas);
        this.type = type;
    }

    public void setListViewLoadMore(ListViewLoadMore mListViewLoadMore) {
        this.mListViewLoadMore = mListViewLoadMore;
        mListViewLoadMore.listViewLoadMore(type);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    /**
     * 基类不知道 你有什么样的布局 所以是抽象的
     *
     * @param huandaiItem
     * @param position
     * @return
     */
    @Override
    public int getViewType(huandaiItem huandaiItem, int position) {

        return R.layout.xuedai_button_3;
    }

    @Override
    public void convert(MyViewHolder_04 helper, huandaiItem item, int position) {
        //int i = Integer.parseInt(item.delay);
        int i1 = getViewType(item, position);
        if (i1 == R.layout.xuedai_button_3) {

//            //电话号码
//            String phone = item.phone1;
//            //名字
//            String short_name = item.short_name;
//            //类别
//            String tag_name = item.tag_name;
//            //图片
//            oder_04.ResultBean.BiddingFilesBean biddingFilesBean = item.bidding_files.get(1);
//            String file_path = biddingFilesBean.file_path;
            String string1 = item.mString1;
            String string2 = item.mString2;
            String string3 = item.mString3;
            String string4 = item.mString4;
            helper.initText(R.id.xuedai_button_3_tv1_plan, string1);
            helper.initText(R.id.xuedai_button_3_tv_fenqi, string2);
            helper.initText(R.id.xuedai_button_3_tv2_plan_day, string3);
            helper.initText(R.id.xuedai_button_3_tv3_plan_day, string4);


        }


    }


    @Override
    public void onLoad() {
        mListViewLoadMore.listViewLoadMore(type);
    }

}
