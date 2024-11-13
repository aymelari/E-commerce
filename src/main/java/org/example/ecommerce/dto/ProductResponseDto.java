package org.example.ecommerce.dto;

import lombok.Builder;
import org.example.ecommerce.entity.Review;

import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Double rating;
    private Date createdAt;
    private Date updatedAt;
    private Set<Long> reviewIds;

}
