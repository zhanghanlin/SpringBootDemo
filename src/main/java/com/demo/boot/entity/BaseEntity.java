package com.demo.boot.entity;

import com.demo.boot.utils.IdGen;
import com.demo.boot.utils.StringUtils;
import com.demo.boot.utils.UserUtils;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    private String id;

    private Date createdAt;

    private String createdBy;

    private Date changedAt;

    private String changedBy;

    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void preInsert() {
        setId(IdGen.uuid());
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())) {
            this.createdBy = user.getUserName();
            this.changedBy = user.getUserName();
        }
        this.createdAt = new Date();
        this.changedAt = this.createdAt;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate() {
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())) {
            this.changedBy = user.getUserName();
        }
        this.changedAt = new Date();
    }
}
