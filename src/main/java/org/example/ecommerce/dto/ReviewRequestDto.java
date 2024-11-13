package org.example.ecommerce.dto;

import org.example.ecommerce.entity.Product;
import org.example.ecommerce.entity.User;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private double rating;
    private String comment;
    private Date reviewDate;
    private Long userId;
    private Long productId;

}
