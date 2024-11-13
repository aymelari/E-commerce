package org.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.enums.Status;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    private Status status;
    private Date paymentDate;
    private Double amount;
    private Long orderId;
}
