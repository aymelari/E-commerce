package org.example.ecommerce.repository;

import org.example.ecommerce.entity.Cart;
import org.example.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
