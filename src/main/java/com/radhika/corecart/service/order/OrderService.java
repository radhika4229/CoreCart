package com.radhika.corecart.service.order;

import com.radhika.corecart.dto.OrderDto;
import com.radhika.corecart.enums.OrderStatus;
import com.radhika.corecart.exceptions.ResourceNotFoundException;
import com.radhika.corecart.model.Cart;
import com.radhika.corecart.model.Order;
import com.radhika.corecart.model.OrderItem;
import com.radhika.corecart.model.Product;
import com.radhika.corecart.repository.OrderRepository;
import com.radhika.corecart.repository.ProductRepository;
import com.radhika.corecart.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Override
    public Order placeOrder(Long userId) {
        Cart cart =cartService.getCartByUserId(userId);
Order order =createOrder(cart);
List<OrderItem> orderItemList=createOrderItems(order,cart);
order.setOrderItem(new HashSet<>(orderItemList));
order.setTotalAmount(calculateToTotalamount(orderItemList));
Order savedOrder=orderRepository.save(order);
cartService.clearCart(cart.getId());
return savedOrder;
    }

private Order createOrder(Cart cart){
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());
        return order;
}


    private List<OrderItem> createOrderItems(Order  order, Cart cart) {
        return cart.getItems().stream().map(cartItem->{
            Product product =cartItem.getProduct();
            product.setInventory(product.getInventory()-cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
                    order,product,cartItem.getQuantity(),cartItem.getUnitPrice()
            );
        }).toList();
    }



    private BigDecimal calculateToTotalamount(List<OrderItem> orderItemList){
        return orderItemList
                .stream()
                .map(item->item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public OrderDto getOrder(Long orderId) {

        return orderRepository.findById(orderId).map(this::convertToDto)
                .orElseThrow(()->new ResourceNotFoundException("No order Found"));
    }

    @Override
    public List<OrderDto> getUserOrders(Long userid){
      List<Order> orders= orderRepository.findByUserId(userid);
      return orders.stream().map(this::convertToDto).toList();
    }
@Override
public OrderDto convertToDto(Order order){
        return modelMapper.map(order,OrderDto.class);
    }

}
