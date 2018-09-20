package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import cn.yfbai.shopbackend.service.ShoppingCartItemService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingCartItemController.class)
public class ShoppingCartItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartItemService shoppingCartItemService;

    private Gson gson = new Gson();

    @Test
    public void should_add_product_to_shopping_cart_and_return_item_id() throws Exception {
        int shoppingCartItemId = 20893;
        ShoppingCartItem shoppingCartItem = SyntaxSugar.createShoppingCartItem().setId(shoppingCartItemId);

        given(shoppingCartItemService.addItemToCart(any())).will(invocation -> {
            ShoppingCartItem item = invocation.getArgument(0);
            item.setId(shoppingCartItemId);
            return item;
        });

        mockMvc.perform(post("/api/users/1/shoppingCartItems")
                .content(gson.toJson(shoppingCartItem))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("id", is(shoppingCartItemId)));
    }

}
