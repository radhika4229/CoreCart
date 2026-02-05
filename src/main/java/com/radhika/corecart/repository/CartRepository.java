package com.radhika.corecart.repository;

import com.radhika.corecart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long userId);
}
