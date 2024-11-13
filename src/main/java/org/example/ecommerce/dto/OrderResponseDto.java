package org.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ecommerce.enums.Status;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long Id;
    private Status status;
    private Date orderDate;
    private Double totalPrice;
    private Long userId;
    private Set<Long> orderItemIds;
}
