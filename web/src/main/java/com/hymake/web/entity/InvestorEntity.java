package com.hymake.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class InvestorEntity {

    private long investorId;
    private String type;
    private String title;
    private String path;
    private Date pushTime;
    private String updateUserid;
    private Date updateTime;
    private String typeName;
    private Date pushTimeStart;
    private Date pushTimeEnd;

    public InvestorEntity() {
    }

    public InvestorEntity(long investorId, String type, String title, String path, Date pushTime, String updateUserid, Date updateTime) {
        this.investorId = investorId;
        this.type = type;
        this.title = title;
        this.path = path;
        this.pushTime = pushTime;
        this.updateUserid = updateUserid;
        this.updateTime = updateTime;
    }

    public long getInvestorId() {
        return this.investorId;
    }

    public void setInvestorId(long investorId) {
        this.investorId = investorId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getPushTime() {
        return this.pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getUpdateUserid() {
        return this.updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getPushTimeStart() {
        return this.pushTimeStart;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setPushTimeStart(Date pushTimeStart) {
        this.pushTimeStart = pushTimeStart;
    }

    public Date getPushTimeEnd() {
        return this.pushTimeEnd;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setPushTimeEnd(Date pushTimeEnd) {
        this.pushTimeEnd = pushTimeEnd;
    }

}