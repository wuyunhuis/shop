package com.shop.entity;

import java.util.Date;

public class Spike {
    private Integer sid;

    private Double price;

    private Date time;

    private Integer state;

    private Integer number;

    private Integer pid;

    private Integer bid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Spike{" +
                "sid=" + sid +
                ", price=" + price +
                ", time=" + time +
                ", state=" + state +
                ", number=" + number +
                ", pid=" + pid +
                ", bid=" + bid +
                '}';
    }
}