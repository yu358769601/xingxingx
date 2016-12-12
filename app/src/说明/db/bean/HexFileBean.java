package com.commonrail.mft.decoder.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by my on 2016/8/26.
 */
@Entity
public class HexFileBean {

    private String brand;
    private String vehicleModel;
    private String engineModel;
    private String power;
    private String project;
    private String version;
    private String fileDate;
    private String downloadUrl;
    @Id
    private String localUrl;
    private String fileLength;
    private String fileMd5;
    private String pv;

    
    public String getFileMd5() {
        return this.fileMd5;
    }
    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }
    public String getFileLength() {
        return this.fileLength;
    }
    public void setFileLength(String fileLength) {
        this.fileLength = fileLength;
    }
    public String getLocalUrl() {
        return this.localUrl;
    }
    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }
    public String getDownloadUrl() {
        return this.downloadUrl;
    }
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    public String getFileDate() {
        return this.fileDate;
    }
    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }
    public String getVersion() {
        return this.version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getProject() {
        return this.project;
    }
    public void setProject(String project) {
        this.project = project;
    }
    public String getPower() {
        return this.power;
    }
    public void setPower(String power) {
        this.power = power;
    }
    public String getEngineModel() {
        return this.engineModel;
    }
    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }
    public String getVehicleModel() {
        return this.vehicleModel;
    }
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    public String getPv() {
        return this.pv;
    }
    public void setPv(String pv) {
        this.pv = pv;
    }
    public String getBrand() {
        return this.brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    @Generated(hash = 598275968)
    public HexFileBean(String brand, String vehicleModel, String engineModel,
            String power, String project, String version, String fileDate,
            String downloadUrl, String localUrl, String fileLength, String fileMd5,
            String pv) {
        this.brand = brand;
        this.vehicleModel = vehicleModel;
        this.engineModel = engineModel;
        this.power = power;
        this.project = project;
        this.version = version;
        this.fileDate = fileDate;
        this.downloadUrl = downloadUrl;
        this.localUrl = localUrl;
        this.fileLength = fileLength;
        this.fileMd5 = fileMd5;
        this.pv = pv;
    }
    @Generated(hash = 2027463714)
    public HexFileBean() {
    }


}
