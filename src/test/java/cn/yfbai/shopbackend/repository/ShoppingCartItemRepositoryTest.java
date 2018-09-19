package cn.yfbai.shopbackend.repository;

import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShoppingCartItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

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
    public void should_return_shopping_cart_items_when_get_by_user_id() {
        Integer userId = 1;
        int quantity = 10;

        Product product = new Product();
        product.setId("020c823b-0753-4107-8216-13d38dde724c");
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, userId, quantity);

        entityManager.persist(shoppingCartItem);

        List<ShoppingCartItem> items = shoppingCartItemRepository.findByUserId(userId);
        assertThat(items.size(), is(1));
        assertThat(items.get(0).getProduct(), equalTo(product));
        assertThat(items.get(0).getQuantity(), is(quantity));
    }

    @Test
    public void should_return_shopping_cart_item_with_id_when_save_new_item() {
        Integer userId = 1;
        int quantity = 10;

        Product product = new Product();
        product.setId("020c823b-0753-4107-8216-13d38dde724c");
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, userId, quantity);

        shoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
        assertThat(shoppingCartItem.getId(), notNullValue());

        List<ShoppingCartItem> items = shoppingCartItemRepository.findByUserId(userId);
        assertThat(items.size(), is(1));
        assertThat(items.get(0).getProduct(), equalTo(product));
        assertThat(items.get(0).getQuantity(), is(quantity));
    }

    @Test
    public void should_return_exist_item_when_query_by_userId_and_productId() {
        Integer userId = 1;
        int quantity = 10;
        Product product = new Product();
        product.setId("020c823b-0753-4107-8216-13d38dde724c");
        ShoppingCartItem existItem = new ShoppingCartItem(product, userId, quantity);

        entityManager.persist(existItem);

        Optional<ShoppingCartItem> findFromRepository = shoppingCartItemRepository.findByProductIdAndUserId(product.getId(), userId);
        assertTrue(findFromRepository.isPresent());
    }
}