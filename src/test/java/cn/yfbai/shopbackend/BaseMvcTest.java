package cn.yfbai.shopbackend;

import cn.yfbai.shopbackend.controller.ProductController;
import cn.yfbai.shopbackend.controller.ProductControllerTest;
import cn.yfbai.shopbackend.controller.ShoppingCartItemController;
import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import cn.yfbai.shopbackend.service.ProductService;
import cn.yfbai.shopbackend.service.ShoppingCartItemService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest({
        ProductController.class,
        ShoppingCartItemController.class
})
@ContextConfiguration(classes = {ShopBackendApplication.class})
public abstract class BaseMvcTest {

    @MockBean
    private ProductService productService;

    @MockBean
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        given(productService.getProducts()).willReturn(SyntaxSugar.createProductList());
        given(shoppingCartItemService.addItemToCart(any())).willReturn(SyntaxSugar.createShoppingCartItem());
    }

}
