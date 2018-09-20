package cn.yfbai.shopbackend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer userId;
    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Product product, Integer userId, int quantity) {
        this.product = product;
        this.userId = userId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public ShoppingCartItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public ShoppingCartItem setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public ShoppingCartItem setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ShoppingCartItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(product, that.product) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, userId, quantity);
    }
}
