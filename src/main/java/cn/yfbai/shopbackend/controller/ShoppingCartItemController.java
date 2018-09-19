package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.service.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingCartItemController {

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @PostMapping("/api/users/{userId}/shoppingCartItems")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCartItem addToCart(@PathVariable Integer userId, @RequestBody ShoppingCartItem shoppingCartItem) {
        shoppingCartItem.setUserId(userId);
        return shoppingCartItemService.addItemToCart(shoppingCartItem);
    }

}
