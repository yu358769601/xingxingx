package com.commonrail.mft.decoder.db.util;

import com.commonrail.mft.decoder.db.FilesDao;
import com.commonrail.mft.decoder.db.HexFileBeanDao;
import com.commonrail.mft.decoder.db.JournalDao;
import com.commonrail.mft.decoder.db.TestDataDao;
import com.commonrail.mft.decoder.db.UserInfoDao;

/**
 * Created by my on 2016/8/26.
 */
public class DbUtil {
    private static HexFileUtil hexFileUtil;
    private static FilesUtil filesUtil;
    private static TestDataUtil testDataUtil;
    private static JournalUtil journalUtil;
    private static UserInfoUtil userInfoUtil;

    private static HexFileBeanDao getHexFileBeanDao() {
        return DbCore.getDaoSession().getHexFileBeanDao();
    }

    public static HexFileUtil getHexFileUtil() {
        if (hexFileUtil == null) {
            hexFileUtil = new HexFileUtil(getHexFileBeanDao());
        }
        return hexFileUtil;
    }

    private static FilesDao getFilesDao() {
        return DbCore.getDaoSession().getFilesDao();
    }

    public static FilesUtil getFilesUtil() {
        if (filesUtil == null) {
            filesUtil = new FilesUtil(getFilesDao());
        }
        return filesUtil;
    }

    private static TestDataDao getTestDataDao() {
        return DbCore.getDaoSession().getTestDataDao();
    }

    public static TestDataUtil getTestDataUtil() {
        if (testDataUtil == null) {
            testDataUtil = new TestDataUtil(getTestDataDao());
        }
        return testDataUtil;
    }

    private static JournalDao getJournalDao() {
        return DbCore.getDaoSession().getJournalDao();
    }

    public static JournalUtil getJournalUtil() {
        if (journalUtil == null) {
            journalUtil = new JournalUtil(getJournalDao());
        }
        return journalUtil;
    }

    private static UserInfoDao getUserInfoDao() {
        return DbCore.getDaoSession().getUserInfoDao();
    }

    public static UserInfoUtil getUserInfoUtil() {
        if (userInfoUtil == null) {
            userInfoUtil = new UserInfoUtil(getUserInfoDao());
        }
        return userInfoUtil;
    }
}
