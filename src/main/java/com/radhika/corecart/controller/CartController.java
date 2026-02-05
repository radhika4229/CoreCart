package com.radhika.corecart.controller;

import com.radhika.corecart.exceptions.ResourceNotFoundException;
import com.radhika.corecart.model.Cart;
import com.radhika.corecart.response.ApiResponse;
import com.radhika.corecart.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {
    private final ICartService cartService;

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
       try{
           Cart cart=cartService.getCart(cartId);
        return ResponseEntity.ok(new ApiResponse("success", cart));
    }
    catch(ResourceNotFoundException e){
       return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
       }
    }


    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try{
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse(" Clear Cart success", null));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }


    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getCartTotalPrice(@PathVariable Long cartId) {
        try{
            BigDecimal totalPrice=cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

}