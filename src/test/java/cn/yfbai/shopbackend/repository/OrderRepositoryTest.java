package cn.yfbai.shopbackend.repository;

import cn.yfbai.shopbackend.entity.Order;
import cn.yfbai.shopbackend.helpers.SyntaxSugar;
import org.aspectj.weaver.ast.Or;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Flyway flyway;

    @Before
    public void setup() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void should_save_order_into_databse() {
        Order order = SyntaxSugar.createOrder();
        order = orderRepository.save(order);

        Order orderFromDatabase = entityManager.find(Order.class, order.getId());

        assertThat(orderFromDatabase, equalTo(order));
        assertThat(orderFromDatabase.getOrderDetails().size(), equalTo(order.getOrderDetails().size()));
    }
}
