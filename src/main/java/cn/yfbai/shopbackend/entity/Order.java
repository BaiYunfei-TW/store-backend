package cn.yfbai.shopbackend.entity;

import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_order")
public class Order {
    @Id
    private Integer id;
    private BigDecimal totalPrice;
    private Integer userId;
    @Transient
    private List<OrderDetail> orderDetails;

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Order setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(userId, order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, userId);
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
