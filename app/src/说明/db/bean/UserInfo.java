package com.commonrail.mft.decoder.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by my on 2016/8/31.
 */
@Entity
public class UserInfo {
    @NotNull
    private String uname;
    @Id
    private String uphone;
    @NotNull
    private String uaddress;
    @NotNull
    private String decoderMac;
    @NotNull
    private String decoderVci;
    @NotNull
    private String cityname;
    public String getCityname() {
        return this.cityname;
    }
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
    public String getDecoderVci() {
        return this.decoderVci;
    }
    public void setDecoderVci(String decoderVci) {
        this.decoderVci = decoderVci;
    }
    public String getDecoderMac() {
        return this.decoderMac;
    }
    public void setDecoderMac(String decoderMac) {
        this.decoderMac = decoderMac;
    }
    public String getUaddress() {
        return this.uaddress;
    }
    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }
    public String getUphone() {
        return this.uphone;
    }
    public void setUphone(String uphone) {
        this.uphone = uphone;
    }
    public String getUname() {
        return this.uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    @Generated(hash = 971226218)
    public UserInfo(@NotNull String uname, String uphone, @NotNull String uaddress,
            @NotNull String decoderMac, @NotNull String decoderVci,
            @NotNull String cityname) {
        this.uname = uname;
        this.uphone = uphone;
        this.uaddress = uaddress;
        this.decoderMac = decoderMac;
        this.decoderVci = decoderVci;
        this.cityname = cityname;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
}
