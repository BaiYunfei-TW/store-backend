package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
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

    @PostMapping("/api/users/{userId}/orders")
    public ResponseEntity createOrder(@PathVariable Integer userId, @RequestBody List<ShoppingCartItem> shoppingCartItems) {
        return ResponseEntity
                .created(URI.create(String.format("/api/users/%d/orders/%d", userId, 1)))
                .build();
    }
}
