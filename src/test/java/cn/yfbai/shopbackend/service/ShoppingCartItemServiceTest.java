package cn.yfbai.shopbackend.service;

import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import cn.yfbai.shopbackend.repository.ShoppingCartItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartItemServiceTest {

    @Mock
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @InjectMocks
    private ShoppingCartItemService shoppingCartItemService;

    @Test
    public void should_add_item_to_shopping_cart_when_the_cart_is_empty() {
        int shoppingCartItemId = 1;

        given(shoppingCartItemRepository.save(any())).will(invocation -> {
            ShoppingCartItem item = invocation.getArgument(0);
            item.setId(shoppingCartItemId);
            return item;
        });

        ShoppingCartItem item = new ShoppingCartItem();
        item.setProduct(new Product());
        item.setUserId(1);
        item = shoppingCartItemService.addItemToCart(item);

        assertThat(item.getId(), is(shoppingCartItemId));
    }

    @Test
    public void should_add_quantity_of_item_when_the_item_exist_in_shopping_cart() {
        Product existProduct = SyntaxSugar.createProduct();

        int existQuantity = 1;
        ShoppingCartItem existItem = SyntaxSugar.createShoppingCartItem();

        given(shoppingCartItemRepository.findByProductIdAndUserId(existProduct.getId(), existItem.getUserId())).willReturn(Optional.of(existItem));
        given(shoppingCartItemRepository.save(any())).will(invocation -> {
            ShoppingCartItem item = invocation.getArgument(0);
            item.setId(existItem.getId());
            return item;
        });

        ShoppingCartItem addedItem = new ShoppingCartItem();
        addedItem.setProduct(existProduct);
        addedItem.setUserId(existItem.getUserId());

        int newQuantity = 10;
        addedItem.setQuantity(newQuantity);

        ShoppingCartItem newItem = shoppingCartItemService.addItemToCart(addedItem);

        assertThat(newItem.getId(), is(existItem.getId()));
        assertThat(newItem.getQuantity(), is(newQuantity + existQuantity));
    }
}