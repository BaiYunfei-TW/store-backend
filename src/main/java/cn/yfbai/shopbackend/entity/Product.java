package cn.yfbai.shopbackend.entity;

import java.math.BigDecimal;

public class Product {
    private String name;
    private String unit;
    private BigDecimal price;
    private Integer totalAmount;
    private String imgUrl;

    public Product() {
    }

    public Product(String name, String unit, BigDecimal price, Integer totalAmount, String imgUrl) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.totalAmount = totalAmount;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
