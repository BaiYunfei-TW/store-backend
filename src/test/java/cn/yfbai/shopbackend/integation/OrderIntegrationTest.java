package cn.yfbai.shopbackend.integation;

import cn.yfbai.shopbackend.entity.Order;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import org.aspectj.weaver.ast.Or;
import org.assertj.core.util.Lists;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIntegrationTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setup() {
        flyway.clean();
        flyway.migrate();

        ShoppingCartItem item = SyntaxSugar.createShoppingCartItem();
        jdbcTemplate.update("INSERT INTO shopping_cart_item(id, product_id, user_id, quantity) VALUES(?, ?, ?, ?)",
                item.getId(),
                item.getProduct().getId(),
                item.getUserId(),
                item.getQuantity());
    }

    @Test
    @Ignore
    public void should_create_order_given_shopping_cart_item_list() {
        List<ShoppingCartItem> shoppingCartItemList = SyntaxSugar.createShoppingCartItemList();

        ResponseEntity responseEntity = testRestTemplate.postForEntity(
                "/api/users/{1}/orders",
                shoppingCartItemList,
                ShoppingCartItem.class,
                SyntaxSugar.USER_ID);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(responseEntity.getHeaders().getLocation(), is(String.format("/api/users/%d/orders/1", SyntaxSugar.USER_ID)));

        List<Order> orders = findOrdersByUserId(SyntaxSugar.USER_ID);
        assertThat(orders.size(), is(1));
        assertThat(orders.get(0).getId(), notNullValue());
        assertThat(orders.get(0).getUserId(), is(SyntaxSugar.USER_ID));
        assertThat(orders.get(0).getTotalPrice(), is(getTotalPrice(shoppingCartItemList)));
    }

    private List<Order> findOrdersByUserId(Integer userId) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE user_id=?", new Object[]{userId}, rs -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotalPrice(rs.getBigDecimal("total_price"));
            return Lists.newArrayList(order);
        });
    }

    private BigDecimal getTotalPrice(List<ShoppingCartItem> shoppingCartItems) {
        return shoppingCartItems.stream().map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce((acc, num) -> acc.add(num))
                .orElse(BigDecimal.ZERO);
    }
}
