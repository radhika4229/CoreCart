package com.radhika.corecart.service.order;

import com.radhika.corecart.dto.OrderDto;
import com.radhika.corecart.model.Order;

import java.util.List;

public interface IOrderService {
Order placeOrder(Long userId);
OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
