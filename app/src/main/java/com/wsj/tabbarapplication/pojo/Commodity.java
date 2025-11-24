package com.wsj.tabbarapplication.pojo;

/**
 * @author: 绫_N
 * @date: 2025/11/20
 * @description: TabBarApplication
 */
public class Commodity {
    private String name,price,desc;
    private int imgId;
    public Commodity(String name, String price, String desc, int imgId) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.imgId = imgId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
