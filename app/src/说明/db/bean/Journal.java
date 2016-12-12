package com.commonrail.mft.decoder.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by Administrator on 2016/10/10.
 */
@Entity
public class Journal {
    private String tree1;
    private String tree2;
    private String tree3;
    private String leaf;
    private String catalog1;
    private String catalog2;
    private String catalog3;
    private String originalPath;
    private String actualPath;
    private String content;
    @Id
    private long operateTime;
    private long process;

    public long getProcess() {
        return this.process;
    }

    public void setProcess(long process) {
        this.process = process;
    }

    public long getOperateTime() {
        return this.operateTime;
    }

    public void setOperateTime(long operateTime) {
        this.operateTime = operateTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getActualPath() {
        return this.actualPath;
    }

    public void setActualPath(String actualPath) {
        this.actualPath = actualPath;
    }

    public String getOriginalPath() {
        return this.originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getCatalog3() {
        return this.catalog3;
    }

    public void setCatalog3(String catalog3) {
        this.catalog3 = catalog3;
    }

    public String getCatalog2() {
        return this.catalog2;
    }

    public void setCatalog2(String catalog2) {
        this.catalog2 = catalog2;
    }

    public String getCatalog1() {
        return this.catalog1;
    }

    public void setCatalog1(String catalog1) {
        this.catalog1 = catalog1;
    }

    public String getLeaf() {
        return this.leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getTree3() {
        return this.tree3;
    }

    public void setTree3(String tree3) {
        this.tree3 = tree3;
    }

    public String getTree2() {
        return this.tree2;
    }

    public void setTree2(String tree2) {
        this.tree2 = tree2;
    }

    public String getTree1() {
        return this.tree1;
    }

    public void setTree1(String tree1) {
        this.tree1 = tree1;
    }

    @Generated(hash = 1485619199)
    public Journal(String tree1, String tree2, String tree3, String leaf,
                   String catalog1, String catalog2, String catalog3, String originalPath,
                   String actualPath, String content, long operateTime, long process) {
        this.tree1 = tree1;
        this.tree2 = tree2;
        this.tree3 = tree3;
        this.leaf = leaf;
        this.catalog1 = catalog1;
        this.catalog2 = catalog2;
        this.catalog3 = catalog3;
        this.originalPath = originalPath;
        this.actualPath = actualPath;
        this.content = content;
        this.operateTime = operateTime;
        this.process = process;
    }

    public Journal copy() {
        Journal cp = new Journal();
        cp.tree1 = this.tree1;
        cp.tree2 = this.tree2;
        cp.tree3 = this.tree3;
        cp.leaf = this.leaf;
        cp.catalog1 = this.catalog1;
        cp.catalog2 = this.catalog2;
        cp.catalog3 = this.catalog3;
        cp.originalPath = this.originalPath;
        cp.actualPath = this.actualPath;
        cp.content = this.content;
        cp.operateTime = this.operateTime;
        cp.process = this.process;
        return cp;
    }

    @Generated(hash = 1562390721)
    public Journal() {
    }

}
