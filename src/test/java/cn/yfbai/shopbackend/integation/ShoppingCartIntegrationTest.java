package cn.yfbai.shopbackend.integation;

import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import cn.yfbai.shopbackend.repository.ShoppingCartItemRepository;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingCartIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    Flyway flyway;

    @Before
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void should_add_product_to_shopping_cart_when_shopping_cart_is_empty() {
        ShoppingCartItem shoppingCartItem = SyntaxSugar.createShoppingCartItem();

        ResponseEntity responseEntity = testRestTemplate.postForEntity("/api/users/1/shoppingCartItems", shoppingCartItem, ShoppingCartItem.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));

        List<ShoppingCartItem> items = shoppingCartItemRepository.findByUserId(shoppingCartItem.getUserId());
        assertThat(items.size(), is(1));
        assertThat(items.get(0).getProduct().getId(), is(shoppingCartItem.getProduct().getId()));
        assertThat(items.get(0).getQuantity(), is(shoppingCartItem.getQuantity()));
    }
}
