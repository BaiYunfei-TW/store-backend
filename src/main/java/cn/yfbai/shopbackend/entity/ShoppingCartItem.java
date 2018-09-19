package cn.yfbai.shopbackend.entity;

import javax.persistence.*;

@Entity
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
