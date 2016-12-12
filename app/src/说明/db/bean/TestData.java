package com.commonrail.mft.decoder.db.bean;

import com.commonrail.mft.decoder.bean.FaultBean;
import com.commonrail.mft.decoder.bean.UnscrambleBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/9/29.
 */
@Entity
public class TestData {
    @Id
    @NotNull
    private long time;

    private String sc;
    private String cl;
    private String dk;

    private String ecu;
    private String fault1;
    private String fault2;
    public String getFault2() {
        return this.fault2;
    }
    public void setFault2(String fault2) {
        this.fault2 = fault2;
    }
    public String getFault1() {
        return this.fault1;
    }
    public void setFault1(String fault1) {
        this.fault1 = fault1;
    }
    public String getEcu() {
        return this.ecu;
    }
    public void setEcu(String ecu) {
        this.ecu = ecu;
    }
    public String getDk() {
        return this.dk;
    }
    public void setDk(String dk) {
        this.dk = dk;
    }
    public String getCl() {
        return this.cl;
    }
    public void setCl(String cl) {
        this.cl = cl;
    }
    public String getSc() {
        return this.sc;
    }
    public void setSc(String sc) {
        this.sc = sc;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    @Generated(hash = 1089941801)
    public TestData(long time, String sc, String cl, String dk, String ecu,
            String fault1, String fault2) {
        this.time = time;
        this.sc = sc;
        this.cl = cl;
        this.dk = dk;
        this.ecu = ecu;
        this.fault1 = fault1;
        this.fault2 = fault2;
    }
    @Generated(hash = 1580692206)
    public TestData() {
    }


}
