package org.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.CartResponseDto;
import org.example.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getUsersCart(@PathVariable Long id){
        return ResponseEntity.ok(cartService.getUsersCart(id));

    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProducttoCart(@RequestBody Long productId,@RequestBody Long userId,@RequestBody int quantity) {
      cartService.addProducttoCart(productId,userId,quantity);
      return ResponseEntity.ok().build();
    }


    @PutMapping("/update")
    public ResponseEntity<Void> updateCartItemQuantity(Long cartItemId,Long userId,int newQuantity) {
        cartService.updateCartItemQuantity(cartItemId,userId,newQuantity);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void>  deleteCartItem(Long cartItemId,Long userId){
        cartService.deleteCartItem(cartItemId,userId);
        return ResponseEntity.ok().build();

    }
}
