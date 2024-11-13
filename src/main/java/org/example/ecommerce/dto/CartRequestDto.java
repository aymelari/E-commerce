package org.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ecommerce.entity.User;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDto {
    private long userId;
    private Set<Long> cartItemIds;


}
