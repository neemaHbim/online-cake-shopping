package com.onlineCakeShopping.App.Repositories;

import com.onlineCakeShopping.App.Domain.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<Cake, Integer> {
}
