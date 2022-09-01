package com.TDD.TestDrivenDevelopment.services;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    private String productCode;

    private Integer amount;

    private BigDecimal unitPrice;
}
