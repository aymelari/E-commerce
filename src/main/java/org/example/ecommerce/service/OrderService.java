package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.OrderRequestDto;
import org.example.ecommerce.dto.OrderResponseDto;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.entity.OrderItem;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.OrderItemRepository;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;

    public void createOrder(OrderRequestDto orderRequestDto) {

        User user=userRepository.findById(orderRequestDto.getUserId()).orElse(null);
        Set<Long> orderItemIds = orderRequestDto.getOrderItemIds();
        Set<OrderItem> orderItemSet=new HashSet<>();
        for(Long orderItemId:orderItemIds){
            OrderItem orderItem =orderItemRepository.findById(orderItemId).orElse(null);
            if(orderItem !=null){
                orderItemSet.add(orderItem);
            }
        }

        Order build = Order.builder()
                .status(orderRequestDto.getStatus())
                .orderDate(orderRequestDto.getOrderDate())
                .totalPrice(orderRequestDto.getTotalPrice())
                .user(user)
                .orderItems(orderItemSet)
                .build();

        orderRepository.save(build);
    }



    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Set<OrderItem> orderItems = order.getOrderItems();
        Set<Long> collect = orderItems.stream().map(orderItem -> orderItem.getId()).collect(Collectors.toSet());


     return   OrderResponseDto.builder()
                .Id(order.getId())
                .status(order.getStatus())
                .orderDate(order.getOrderDate())
                .totalPrice(order.getTotalPrice())
                .userId(order.getUser().getId())
                .orderItemIds(collect).build();

    }



    public Set<OrderResponseDto> getAllOrders(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        Set<Order> orders = user.getOrder();

        Set<OrderResponseDto> orderResponseDtos =new HashSet<>();
        for(Order order : orders)
        {
            Set<Long> collect1 = order.getOrderItems().stream().map(orderItem -> orderItem.getId()).collect(Collectors.toSet());
            OrderResponseDto build = OrderResponseDto.builder()
                    .Id(order.getId())
                    .status(order.getStatus())
                    .orderDate(order.getOrderDate())
                    .totalPrice(order.getTotalPrice())
                    .userId(userId)
                    .orderItemIds(collect1).build();
            orderResponseDtos.add(build);
        }

        return orderResponseDtos;


    }


}
