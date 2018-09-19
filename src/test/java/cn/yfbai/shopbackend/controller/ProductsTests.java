package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductsTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_product_list_when_call_get_products_list() {
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                "/api/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

        List<Product> productList = responseEntity.getBody();
        assertThat(productList.size(), is(2));

        Product product = productList.get(0);
        assertThat(product.getName(), is("可乐"));
        assertThat(product.getPrice(), is(BigDecimal.valueOf(4.5)));
        assertThat(product.getUnit(), is("瓶"));
        assertThat(product.getTotalAmount(), is(10));
        assertThat(product.getImgUrl(), is("/api/img/1"));
    }

}
