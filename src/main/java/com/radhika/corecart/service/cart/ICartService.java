package com.radhika.corecart.service.cart;

import com.radhika.corecart.model.Cart;
import com.radhika.corecart.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

   Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
