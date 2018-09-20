package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.Order;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import cn.yfbai.shopbackend.service.OrderService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;

    private Gson gson = new Gson();

    @Test
    public void should_return_order_location_when_create_an_order() throws Exception {
        List<ShoppingCartItem> shoppingCartItemList = SyntaxSugar.createShoppingCartItemList();

        given(orderService.createOrder(anyList(), any())).will(invocation -> new Order()
                .setId(2)
                .setUserId(invocation.getArgument(1))
                .setTotalPrice(SyntaxSugar.getTotalPrice(shoppingCartItemList)));

        mockMvc.perform(post("/api/users/1/orders")
                .content(gson.toJson(shoppingCartItemList))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/api/users/1/orders/2"));

        verify(orderService).createOrder(anyList(), any());
    }
}
