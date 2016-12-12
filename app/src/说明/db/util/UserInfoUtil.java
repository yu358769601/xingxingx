package com.commonrail.mft.decoder.db.util;


import com.commonrail.mft.decoder.db.TestDataDao;
import com.commonrail.mft.decoder.db.UserInfoDao;
import com.commonrail.mft.decoder.db.bean.TestData;
import com.commonrail.mft.decoder.db.bean.UserInfo;


/**
 * Created by my on 2016/8/26.
 */
public class UserInfoUtil extends DbBaseUtil<UserInfo, Long> {
    public UserInfoUtil(UserInfoDao dao) {
        super(dao);
    }
}
