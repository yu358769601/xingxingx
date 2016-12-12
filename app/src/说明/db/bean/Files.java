package com.commonrail.mft.decoder.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by my on 2016/8/31.
 */
@Entity
public class Files {
    @Id
    private Long id;
    @NotNull
    private String fileType;
    @NotNull
    @Unique
    private String fileUrl;
    @NotNull
    private String fileLocalUrl;
    @NotNull
    private String fileLen;
    @NotNull
    private int fileStatus;

    public int getFileStatus() {
        return this.fileStatus;
    }
    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }
    public String getFileLen() {
        return this.fileLen;
    }
    public void setFileLen(String fileLen) {
        this.fileLen = fileLen;
    }
    public String getFileLocalUrl() {
        return this.fileLocalUrl;
    }
    public void setFileLocalUrl(String fileLocalUrl) {
        this.fileLocalUrl = fileLocalUrl;
    }
    public String getFileUrl() {
        return this.fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getFileType() {
        return this.fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1240651352)
    public Files(Long id, @NotNull String fileType, @NotNull String fileUrl,
            @NotNull String fileLocalUrl, @NotNull String fileLen, int fileStatus) {
        this.id = id;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
        this.fileLocalUrl = fileLocalUrl;
        this.fileLen = fileLen;
        this.fileStatus = fileStatus;
    }
    @Generated(hash = 1274349608)
    public Files() {
    }
}
