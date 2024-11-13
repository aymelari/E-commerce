package org.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.PaymentRequestDto;
import org.example.ecommerce.dto.PaymentResponseDto;
import org.example.ecommerce.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> makePayment(@RequestBody PaymentRequestDto paymentRequestDto){
        paymentService.makePayment(paymentRequestDto);
     return   ResponseEntity.ok().build();
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable Long paymentId){
        return ResponseEntity.ok(paymentService.getPayment(paymentId));
    }
}
