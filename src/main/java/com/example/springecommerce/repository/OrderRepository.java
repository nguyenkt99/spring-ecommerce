package com.example.springecommerce.repository;

import com.example.springecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(long userId);

    @Query("SELECT o FROM Order o WHERE o.user.id = ?1 AND o.id = ?2")
    Order findByUserIdAndOrderId(long userId, long orderId);
}
