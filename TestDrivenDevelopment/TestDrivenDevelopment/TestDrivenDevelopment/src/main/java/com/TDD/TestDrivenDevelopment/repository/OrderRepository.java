package com.TDD.TestDrivenDevelopment.repository;

import com.TDD.TestDrivenDevelopment.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
