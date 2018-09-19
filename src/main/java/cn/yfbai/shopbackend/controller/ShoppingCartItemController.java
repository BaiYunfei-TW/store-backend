package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartItemController {

    @PostMapping("/api/users/{userId}/shoppingCartItems")
    public ShoppingCartItem addToCart(@PathVariable Integer userId, @RequestBody ShoppingCartItem shoppingCartItem) {
        shoppingCartItem.setId(1);
        return shoppingCartItem;
    }
}
