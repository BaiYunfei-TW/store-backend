package cn.yfbai.shopbackend.service;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.repository.ShoppingCartItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        item = shoppingCartItemService.addItemToCart(item);

        assertThat(item.getId(), is(shoppingCartItemId));
    }
}