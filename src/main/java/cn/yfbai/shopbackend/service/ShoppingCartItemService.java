package cn.yfbai.shopbackend.service;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.repository.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    public ShoppingCartItem addItemToCart(ShoppingCartItem shoppingCartItem) {
        return shoppingCartItemRepository.save(shoppingCartItem);
    }
}
