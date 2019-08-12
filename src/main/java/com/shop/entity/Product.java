package com.shop.entity;

import java.util.Date;

public class Product {
    private Integer pid;

    private String pname;

    private Double price;

    private String image;

    private String details;

    private Integer hot;

    private Date pdate;

    private Double taxation;

    private Double postage;

    private Integer repertory;

    private Integer sales;

    private String source;

    private Integer wholesale;

    private Integer cid;

    private Integer bid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Double getTaxation() {
        return taxation;
    }

    public void setTaxation(Double taxation) {
        this.taxation = taxation;
    }

    public Double getPostage() {
        return postage;
    }

    public void setPostage(Double postage) {
        this.postage = postage;
    }

    public Integer getRepertory() {
        return repertory;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getWholesale() {
        return wholesale;
    }

    public void setWholesale(Integer wholesale) {
        this.wholesale = wholesale;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", details='" + details + '\'' +
                ", hot=" + hot +
                ", pdate=" + pdate +
                ", taxation=" + taxation +
                ", postage=" + postage +
                ", repertory=" + repertory +
                ", sales=" + sales +
                ", source='" + source + '\'' +
                ", wholesale=" + wholesale +
                ", cid=" + cid +
                ", bid=" + bid +
                '}';
    }
}