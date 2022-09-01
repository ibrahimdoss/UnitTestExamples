package com.TDD.TestDrivenDevelopment.services;

import com.TDD.TestDrivenDevelopment.clients.PaymentClient;
import com.TDD.TestDrivenDevelopment.dtos.OrderDto;
import com.TDD.TestDrivenDevelopment.models.Order;
import com.TDD.TestDrivenDevelopment.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    public OrderService(OrderRepository orderRepository, PaymentClient paymentClient){
        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;
    }
    public OrderDto createOrder(CreateOrderRequest request) {
       // return new OrderDto();          //test1'deki caseden alınan hatanın baslangıc cözümü.

        /*return OrderDto.builder()
                .totalPrice(BigDecimal.valueOf(61.5))
                .build();*/ /*test 2 deki hatanın giderilmesi icin günü kurtarma amaclı 61.5 degeri döndük kü test case
                success versin.*/

        BigDecimal totalPrice = request.getUnitPrice().multiply(BigDecimal.valueOf(request.getAmount()));/*test4 icin alınan
        hatanın cözümü.*/
        Order order = Order.builder().totalPrice(totalPrice).build();
        this.paymentClient.pay(order);
        Order save = this.orderRepository.save(order);
        return OrderDto.builder().totalPrice(totalPrice).build();
    }
}
