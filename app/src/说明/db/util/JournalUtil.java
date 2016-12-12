package com.commonrail.mft.decoder.db.util;

import com.commonrail.mft.decoder.db.JournalDao;
import com.commonrail.mft.decoder.db.bean.Journal;

/**
 * Created by Administrator on 2016/10/10.
 */
public class JournalUtil extends DbBaseUtil<Journal, Long>  {
    public JournalUtil(JournalDao dao) {
        super(dao);
    }
}
