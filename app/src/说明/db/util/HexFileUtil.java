package com.commonrail.mft.decoder.db.util;

import com.commonrail.mft.decoder.db.HexFileBeanDao;
import com.commonrail.mft.decoder.db.bean.HexFileBean;


/**
 * Created by my on 2016/8/26.
 */
public class HexFileUtil extends DbBaseUtil<HexFileBean, Long> {
    public HexFileUtil(HexFileBeanDao dao) {
        super(dao);
    }
}
