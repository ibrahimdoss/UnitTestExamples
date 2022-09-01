package com.TDD.TestDrivenDevelopment.services;

import com.TDD.TestDrivenDevelopment.clients.PaymentClient;
import com.TDD.TestDrivenDevelopment.dtos.OrderDto;
import com.TDD.TestDrivenDevelopment.models.Order;
import com.TDD.TestDrivenDevelopment.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService; //hangi classı test ediyorsak inject mock deriz, hangi classı kullanıyorsak da mock deriz.

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentClient paymentClient;

    public static Stream<Arguments> order_requests() { /*MethodSource*/
        return Stream.of(
                Arguments.of("code1", 5, BigDecimal.valueOf(12.3), BigDecimal.valueOf(61.5),
                        Arguments.of("code2", 10, BigDecimal.valueOf(15), BigDecimal.valueOf(150))));
    }

    @Test
    public void it_should_create_order_with_5_items(){
        // given
        CreateOrderRequest request = CreateOrderRequest.builder()
                .productCode("code1")
                .amount(5)
                .unitPrice(BigDecimal.valueOf(12.3))
                .build();

        // when
        OrderDto order = orderService.createOrder(request);

        // then
       then(order).isNotNull();   /*test 1*/
       then(order.getTotalPrice()).isEqualTo(BigDecimal.valueOf(61.5)); /* test2*/

    }

    @Test
    public void it_should_create_order_with_10_items(){
        // given
        CreateOrderRequest request = CreateOrderRequest.builder()
                .productCode("code1")
                .amount(10)
                .unitPrice(BigDecimal.valueOf(15))
                .build();

        // when
        OrderDto order = orderService.createOrder(request);

        // then
        then(order).isNotNull();   /*test 3*/
        then(order.getTotalPrice()).isEqualTo(BigDecimal.valueOf(150)); /* test4*/

    }

    @ParameterizedTest
    @MethodSource("order_requests") //order_requests adında yukarıda olusturup argümanları yukarıda belirledik.
    public void it_should_create_orders(String productCode, Integer amount, BigDecimal unitPrice,
                                        BigDecimal totalPrice){ // buraya methodSource icerisinde olusturduğumuz argümanların
                                                                // degiskenlerini geciyoruz.(adını)
        //given
        CreateOrderRequest request = CreateOrderRequest.builder()
                .productCode(productCode)
                        .unitPrice(unitPrice)
                                .amount(amount)
                                        .build();

        Order order = new Order();
        order.setId(131231);
        when(orderRepository.save(any())).thenReturn(order);

        //when
        OrderDto orderDto = orderService.createOrder(request);  /*OrderDto order = local variable.*/

        //then
        then(orderDto.getTotalPrice()).isEqualTo(totalPrice);
    }

    @Test
    public void it_should_fail_order_creation_when_payment_failed(){
        //given
        CreateOrderRequest request = CreateOrderRequest.builder()
                .productCode("code1")
                .unitPrice(BigDecimal.valueOf(12))
                .amount(3)
                .build();

        //when
        doThrow(new IllegalArgumentException()).when(paymentClient).pay(any()); /*doThrow1*/

       Throwable throwable = catchThrowable(() -> { orderService.createOrder(request);}); /*burada cıktıyı alıp.*/

        //then
        then(throwable).isInstanceOf(IllegalArgumentException.class); // burada illegalexception alması gerek.
        verifyNoInteractions(orderRepository); /*veriyf1*/
    }
}



/*ParameterizedTest bir test icinde farklı parametreler girip farklı cıktılar beklenilen test.
* Yani logic aynı pattern aynı olduğu zaman bu testi uygulayabiliriz.*/


//verify1 = Yukarıda payment method fail ederse orderRepository'nin save methodunu hic cagırmasın demis oluyoruz.
/*doThrow1  Yukarıda payment methodun pay metodunu çağırınca exception fırlatacak.
void methodlarda doThrow ile bunu yaparız.*/