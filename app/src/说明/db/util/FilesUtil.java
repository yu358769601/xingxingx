package com.commonrail.mft.decoder.db.util;

import com.commonrail.mft.decoder.db.FilesDao;
import com.commonrail.mft.decoder.db.HexFileBeanDao;
import com.commonrail.mft.decoder.db.bean.Files;
import com.commonrail.mft.decoder.db.bean.HexFileBean;


/**
 * Created by my on 2016/8/26.
 */
public class FilesUtil extends DbBaseUtil<Files, Long> {
    public FilesUtil(FilesDao dao) {
        super(dao);
    }
}
