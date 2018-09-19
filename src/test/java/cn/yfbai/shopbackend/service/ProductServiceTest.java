package cn.yfbai.shopbackend.service;

import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.repository.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void should_get_product_list_when_query() {
        when(productRepository.findAll()).thenReturn(Lists.newArrayList(
                new Product("可乐", "瓶", BigDecimal.valueOf(4.5), 10, "/api/img/1"),
                new Product("雪碧", "瓶", BigDecimal.valueOf(4.5), 10, "/api/img/2")
        ));

        List<Product> productList = productService.getProducts();
        assertThat(productList.size(), is(2));
    }

}
