package cn.yfbai.shopbackend.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public OrderDetail setId(Integer id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderDetail setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderDetail setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
