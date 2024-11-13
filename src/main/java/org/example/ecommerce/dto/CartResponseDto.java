package org.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ecommerce.entity.CartItem;
import org.example.ecommerce.entity.User;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {
    private long id;
    private  Long userId;
    private Set<Long> cartItemIds;
}
