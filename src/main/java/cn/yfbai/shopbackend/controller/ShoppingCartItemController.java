package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.service.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartItemController {

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @PostMapping("/api/users/{userId}/shoppingCartItems")
    public ShoppingCartItem addToCart(@PathVariable Integer userId, @RequestBody ShoppingCartItem shoppingCartItem) {
        shoppingCartItem.setUserId(userId);
        return shoppingCartItemService.addItemToCart(shoppingCartItem);
    }

}
