package org.example.ecommerce.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ecommerce.entity.Review;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private String description;
    private double price;
    private int stock;
    private Double rating;
    private Date createdAt;
    private Date updatedAt;
    private Set<Long> reviewIds;
}
