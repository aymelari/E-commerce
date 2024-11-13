package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.PaymentRequestDto;
import org.example.ecommerce.dto.PaymentResponseDto;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.entity.Payment;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public void makePayment(PaymentRequestDto paymentRequestDto){
        Order order=orderRepository.findById(paymentRequestDto.getOrderId()).orElse(null);
        Payment build = Payment.builder()
                .status(paymentRequestDto.getStatus())
                .paymentDate(paymentRequestDto.getPaymentDate())
                .amount(paymentRequestDto.getAmount())
                .order(order).build();
        paymentRepository.save(build);
    }


    public PaymentResponseDto getPayment(Long paymentId){
        Payment payment=paymentRepository.findById(paymentId).orElse(null);
        PaymentResponseDto build = PaymentResponseDto.builder()
                .id(paymentId)
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .amount(payment.getAmount())
                .orderId(payment.getOrder().getId())
                .build();

        return build;

    }
}
