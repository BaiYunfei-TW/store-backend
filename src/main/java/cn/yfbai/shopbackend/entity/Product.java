package cn.yfbai.shopbackend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
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

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Product setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Product setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Product setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
