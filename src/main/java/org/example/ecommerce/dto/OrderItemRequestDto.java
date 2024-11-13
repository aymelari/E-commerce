package org.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDto {
    private int quantity;
    private double price;
    private Long orderId;
    private Long productId;


}
