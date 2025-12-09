package com.wsj.tabbarapplication.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: 绫_N
 * @date: 2025/12/8
 * @description: myAppDemo_wsj
 */
public class Release {
    private Integer id;
    private String title;
    private BigDecimal price;
    private String description;
    private String imagePath;
    private LocalDateTime createTime;

    public Release(Integer id, String title, BigDecimal price, String description, String imagePath, LocalDateTime createTime) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
