package com.xinxinxuedai.MVP.ApplicationStatusActivity;

import android.content.Context;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.bean.GetLoanDetail;
import com.xinxinxuedai.request.GetLoanDetail_Request;
import com.xinxinxuedai.request.NetWorkCallBack;

/**
 * Created by 35876 于萌萌
 * 创建日期: 13:50 . 2016年12月06日
 * 描述:申请状态_P
 * <p>
 * <p>
 * 备注:
 */

public class ApplicationStatusActivity_P extends BaseMvp<ApplicationStatusActivity_C> implements ApplicationStatusActivity_M{

    static Context context;
    public ApplicationStatusActivity_P(Context context){
        this.context = context;
    }

    ApplicationStatusActivity_C applicationStatusActivity_c;
    @Override
    public void setCallBack(ApplicationStatusActivity_C applicationStatusActivity_c) {
        this.applicationStatusActivity_c = applicationStatusActivity_c;
    }


    /**
     * 获取网络数据 借款状态的数据
     */
    @Override
    public void getData() {
     // UtilsToast.showToast(context, "获取网络借款状态中~");
        //测试页面
        GetLoanDetail_Request.request(context, new NetWorkCallBack<GetLoanDetail>() {
            @Override
            public void onSucceed(GetLoanDetail detail,int dataMode) {
                applicationStatusActivity_c.setData(detail);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });




//        //获取到了网络数据
//        ApplicationStatusData applicationStatusData = new ApplicationStatusData("1", "2", "3", "4", "5");
//
//        applicationStatusActivity_c.setData(applicationStatusData);
    }
}
