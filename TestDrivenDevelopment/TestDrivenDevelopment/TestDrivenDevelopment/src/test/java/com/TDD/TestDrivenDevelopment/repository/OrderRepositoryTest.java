package com.TDD.TestDrivenDevelopment.repository;

import com.TDD.TestDrivenDevelopment.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");

    @Test
    public void it_should_find_orders(){

        //given
        Order order1 = Order.builder().totalPrice(BigDecimal.TEN).build();
        Order order2 = Order.builder().totalPrice(BigDecimal.valueOf(2)).build();

        Object id1 = this.testEntityManager.persistAndGetId(order1);
        Object id2 = this.testEntityManager.persistAndGetId(order2);
        this.testEntityManager.flush();

        //when
        List<Order> orders = this.orderRepository.findAll();

        //then
        then(orders).isNotEmpty();
        Order o1 = orders.get(0);
        Order o2 = orders.get(1);
        then(o1.getId()).isEqualTo(id1);
        then(o1.getId()).isEqualTo(id2);
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name",postgreSQLContainer::getDriverClassName);

        /*Burada bunlar?? ekleme sebebimiz, test containers static yani burada  postgreSQLContainer static oldugu icin
        * buraya test dosyas??na ait bir sey koyup cal??st??ramay??z c??nk?? test container sonradan geliyor.
        * Eklemek icin de su sekilde postgreSQLContainer aya??a kalk??yor  aya??a kalkt??ktan sonra DynamicPropertySource
        * alt??nda yazd??klar??m??z?? set ediyor.
        *Dolay??s??yla testEntityManager cag??rd??????m??z zaman gidip o datasource ??zerinden geliyor. */
    }
}

// Bu test cal??st??g??nda bu testin testcontainersa ba??land??????n?? da DynamicPropertySource ??zerinden yap??yoruz.