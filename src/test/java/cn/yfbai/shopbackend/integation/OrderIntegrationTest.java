package cn.yfbai.shopbackend.integation;

import cn.yfbai.shopbackend.entity.Order;
import cn.yfbai.shopbackend.entity.OrderDetail;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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
    public void should_create_order_given_shopping_cart_item_list() {
        List<ShoppingCartItem> shoppingCartItemList = SyntaxSugar.createShoppingCartItemList();

        ResponseEntity responseEntity = testRestTemplate.postForEntity(
                "/api/users/{1}/orders",
                shoppingCartItemList,
                ShoppingCartItem.class,
                SyntaxSugar.USER_ID);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(responseEntity.getHeaders().getLocation().getPath(), is(String.format("/api/users/%d/orders/1", SyntaxSugar.USER_ID)));

        List<Order> orders = findOrdersByUserId(SyntaxSugar.USER_ID);
        assertThat(orders.size(), is(1));
        assertThat(orders.get(0).getId(), notNullValue());
        assertThat(orders.get(0).getUserId(), is(SyntaxSugar.USER_ID));
        assertThat(orders.get(0).getOrderDetails().size(), is(shoppingCartItemList.size()));
        assertThat(orders.get(0).getTotalPrice(), is(SyntaxSugar.getTotalPrice(shoppingCartItemList).setScale(2)));
    }

    private List<Order> findOrdersByUserId(Integer userId) {
        return jdbcTemplate.query("SELECT * FROM shopping_order WHERE user_id = ?", new Object[]{userId}, rs -> {
            List<Order> orders = new ArrayList<>();
            while(rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalPrice(rs.getBigDecimal("total_price"));
                order.setOrderDetails(findOrderDetailsByOrderId(order.getId()));
                orders.add(order);
            }
            return orders;
        });
    }

    private List<OrderDetail> findOrderDetailsByOrderId(Integer orderId) {
        return jdbcTemplate.query("SELECT * FROM order_detail WHERE order_id = ?", new Object[]{orderId}, rs -> {
            List<OrderDetail> orderDetails = new ArrayList<>();
            while(rs.next()) {
                OrderDetail order = new OrderDetail();
                order.setId(rs.getInt("id"));
                order.setQuantity(rs.getInt("quantity"));

                orderDetails.add(order);
            }
            return orderDetails;
        });
    }

}
