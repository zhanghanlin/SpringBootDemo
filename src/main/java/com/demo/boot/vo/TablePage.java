package com.demo.boot.vo;

import java.io.Serializable;
import java.util.List;

public class TablePage<E> implements Serializable {

    private static final long serialVersionUID = -5487946482133940671L;

    private int draw; // 来自客户端 sEcho 的没有变化的复制品

    private long recordsTotal; // 实际的行数

    private long recordsFiltered; // 过滤之后实际的行数。

    private List<E> data;

    public TablePage(int draw, long recordsTotal, long recordsFiltered, List<E> data) {
        super();
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}