package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void should_get_product_list_when_get_product_list() throws Exception {
        given(productService.getProducts()).willReturn(getProducts());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].name").value("可乐"))
                .andExpect(jsonPath("$[1].name").value("雪碧"));

        verify(productService).getProducts();
    }

    @Test
    public void should_get_empty_product_list_when_the_list_is_empty() throws Exception {
        given(productService.getProducts()).willReturn(new ArrayList<>());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(productService).getProducts();
    }

    private List<Product> getProducts() {
        return Arrays.asList(
                new Product("可乐", "瓶", BigDecimal.valueOf(4.5), 10, "/api/img/1"),
                new Product("雪碧", "瓶", BigDecimal.valueOf(4.5), 10, "/api/img/2")
        );
    }
}
