package com.onlineCakeShopping.App.Repositories;

import com.onlineCakeShopping.App.Domain.Cake;
import com.onlineCakeShopping.App.Domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
