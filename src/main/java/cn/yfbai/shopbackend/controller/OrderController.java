package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.Order;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/api/users/{userId}/orders")
    public ResponseEntity createOrder(@PathVariable Integer userId, @RequestBody List<ShoppingCartItem> shoppingCartItems) {
        Order order = orderService.createOrder(shoppingCartItems, userId);
        return ResponseEntity
                .created(URI.create(String.format("/api/users/%d/orders/%d", order.getUserId(), order.getId())))
                .build();
    }
}
