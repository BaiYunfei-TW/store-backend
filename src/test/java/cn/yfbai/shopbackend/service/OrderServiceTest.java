package cn.yfbai.shopbackend.service;

import cn.yfbai.shopbackend.entity.Order;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import cn.yfbai.shopbackend.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void should_return_order_given_shopping_cart_item_list() {
        given(orderRepository.save(any(Order.class))).will(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId(SyntaxSugar.ORDER_ID);
            return order;
        });

        List<ShoppingCartItem> shoppingCartItemList = SyntaxSugar.createShoppingCartItemList();
        Order order = orderService.createOrder(shoppingCartItemList, SyntaxSugar.USER_ID);

        assertThat(order.getId(), is(SyntaxSugar.ORDER_ID));
        assertThat(order.getUserId(), is(SyntaxSugar.USER_ID));
        assertThat(order.getTotalPrice(), is(SyntaxSugar.getTotalPrice(shoppingCartItemList)));
        assertThat(order.getOrderDetails().size(), is(shoppingCartItemList.size()));

        verify(orderRepository).save(any(Order.class));
    }
}
