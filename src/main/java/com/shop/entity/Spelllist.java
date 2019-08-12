package com.shop.entity;

public class Spelllist {
    private Integer sid;

    private Double price;

    private Integer number;

    private Integer eixtnum;

    private Integer state;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEixtnum() {
        return eixtnum;
    }

    public void setEixtnum(Integer eixtnum) {
        this.eixtnum = eixtnum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        return "Spelllist{" +
                "sid=" + sid +
                ", price=" + price +
                ", number=" + number +
                ", eixtnum=" + eixtnum +
                ", state=" + state +
                ", pid=" + pid +
                ", bid=" + bid +
                '}';
    }
}