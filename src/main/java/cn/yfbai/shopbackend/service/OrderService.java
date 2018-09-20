package cn.yfbai.shopbackend.service;

import cn.yfbai.shopbackend.entity.Order;
import cn.yfbai.shopbackend.entity.OrderDetail;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(List<ShoppingCartItem> shoppingCartItems, Integer userId) {
        Order order = new Order()
                .setUserId(userId)
                .setTotalPrice(getTotalPrice(shoppingCartItems));
        List<OrderDetail> orderDetails = new ArrayList<>(shoppingCartItems.size());
        shoppingCartItems.forEach(item -> orderDetails.add(new OrderDetail())
        );
        order.setOrderDetails(orderDetails);
        return orderRepository.save(order);
    }

    private BigDecimal getTotalPrice(List<ShoppingCartItem> shoppingCartItems) {
        return shoppingCartItems.stream().map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce((acc, num) -> acc.add(num))
                .orElse(BigDecimal.ZERO);
    }
}
