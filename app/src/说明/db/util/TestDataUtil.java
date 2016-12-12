package com.commonrail.mft.decoder.db.util;


import com.commonrail.mft.decoder.db.TestDataDao;
import com.commonrail.mft.decoder.db.bean.TestData;


/**
 * Created by my on 2016/8/26.
 */
public class TestDataUtil extends DbBaseUtil<TestData, Long> {
    public TestDataUtil(TestDataDao dao) {
        super(dao);
    }
}
