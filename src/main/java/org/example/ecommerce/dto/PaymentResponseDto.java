package org.example.ecommerce.dto;

import lombok.Builder;
import org.example.ecommerce.enums.Status;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {
    private long id;
    private Status status;
    private Date paymentDate;
    private Double amount;
    private Long orderId;
}
