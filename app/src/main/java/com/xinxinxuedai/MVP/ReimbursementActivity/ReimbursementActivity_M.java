package com.xinxinxuedai.MVP.ReimbursementActivity;

import com.andview.refreshview.XRefreshView;
import com.xinxinxuedai.view.MyListView;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:27 . 2016年12月07日
 * 描述:我要还款_M
 * <p>
 * <p>
 * 备注:
 */

public interface ReimbursementActivity_M {
    /**
     * 点击了支付
     */
    void topUp();

    /**
     * 获取我要还贷界面列表的数据
     * @param reimbursement_lv
     */
    void initListViewData(MyListView reimbursement_lv);

    /**
     * 初始化刷新
     */
    void initRefurbish(XRefreshView xRefreshView);

    /**
     * 提前还款访问网络
     */
    void subTiQianHuanKuan();
}
