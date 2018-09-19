package cn.yfbai.shopbackend.repository;

import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShoppingCartItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

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
}