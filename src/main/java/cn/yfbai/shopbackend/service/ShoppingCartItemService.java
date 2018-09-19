package cn.yfbai.shopbackend.service;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.repository.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    public ShoppingCartItem addItemToCart(ShoppingCartItem shoppingCartItem) {
        Optional<ShoppingCartItem> existItem = shoppingCartItemRepository.findByProductIdAndUserId(
                shoppingCartItem.getProduct().getId(), shoppingCartItem.getUserId());
        existItem.ifPresent(item -> {
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + item.getQuantity());
        });
        return shoppingCartItemRepository.save(shoppingCartItem);
    }
}
