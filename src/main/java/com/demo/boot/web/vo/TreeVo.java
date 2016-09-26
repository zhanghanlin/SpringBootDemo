package com.demo.boot.web.vo;

import java.io.Serializable;

public class TreeVo implements Serializable {

    private static final long serialVersionUID = 5128073200334748622L;
    private String id;
    private String pId;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
