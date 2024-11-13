package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.CartResponseDto;
import org.example.ecommerce.entity.Cart;
import org.example.ecommerce.entity.CartItem;
import org.example.ecommerce.entity.Product;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.CartItemRepository;
import org.example.ecommerce.repository.CartRepository;
import org.example.ecommerce.repository.ProductRepository;
import org.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartResponseDto getUsersCart(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Cart byUser = cartRepository.findByUser(user);
        Set<Long> collect = byUser.getCartItems().stream().map(cartItem -> cartItem.getId()).collect(Collectors.toSet());
        CartResponseDto build = CartResponseDto.builder()
                .userId(id)
                .id(byUser.getId())
                .cartItemIds(collect)
                .build();
        return build;
    }

   public void addProducttoCart(Long productId,Long userId,int quantity){
       Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));
       CartResponseDto usersCart = getUsersCart(userId);
       Cart cart = cartRepository.findById(usersCart.getId()).orElseThrow(()->new RuntimeException("cart not found") );
       CartItem newCartItem = CartItem.builder()
               .quantity(quantity)
               .cart(cart)
               .product(product)
               .build();
       cart.getCartItems().add(newCartItem);
       cartRepository.save(cart);



   }

    public void updateCartItemQuantity(Long cartItemId,Long userId,int newQuantity){
        CartResponseDto usersCart = getUsersCart(userId);
        Cart cart = cartRepository.findById(usersCart.getId()).orElseThrow(()->new RuntimeException("cart not found") );

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("cartItem not found"));
        if (cartItem.getCart().getId()!=(cart.getId())) {
            throw new RuntimeException("Cart item does not belong to the user's cart");
        }


        cartItem.setQuantity(newQuantity);
         cartItemRepository.save(cartItem);


    }


    public void deleteCartItem(Long cartItemId,Long userId){
        CartResponseDto usersCart = getUsersCart(userId);
        Cart cart = cartRepository.findById(usersCart.getId()).orElseThrow(()->new RuntimeException("cart not found") );
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("cartItem not found"));
        if (cartItem.getCart().getId()!=(cart.getId())) {
            throw new RuntimeException("Cart item does not belong to the user's cart");

        }

        cartItemRepository.deleteById(cartItemId);
    }





}
