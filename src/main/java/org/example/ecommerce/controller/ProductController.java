package org.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.*;
import org.example.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> productById(@RequestBody @PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@RequestBody @PathVariable Long id, @RequestBody  ProductRequestDto productRequestDto){
        productService.updateProductById(id,productRequestDto);
        return ResponseEntity.ok().build();


    }

    @PostMapping
    public ResponseEntity<Void> newProduct(@RequestBody ProductRequestDto productRequestDto){
        productService.createProduct(productRequestDto);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{productId}/reviews")
    public ResponseEntity<Void> addReview(@PathVariable Long id,@RequestBody ReviewRequestDto reviewRequestDto){
        productService.addReview(id,reviewRequestDto);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{productId}/reviews")
    public ResponseEntity<Set<ReviewResponseDto>> getReviewsByProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getReviewsByProduct(id));
    }

    }
