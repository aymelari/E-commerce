package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.ProductRequestDto;
import org.example.ecommerce.dto.ProductResponseDto;
import org.example.ecommerce.dto.ReviewRequestDto;
import org.example.ecommerce.dto.ReviewResponseDto;
import org.example.ecommerce.entity.Product;
import org.example.ecommerce.entity.Review;
import org.example.ecommerce.repository.ProductRepository;
import org.example.ecommerce.repository.ReviewRepository;
import org.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Set<Review> reviews = product.getReviews();
        Set<Long> collect = reviews.stream().map(review -> review.getId()).collect(Collectors.toSet());
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .reviewIds(collect)
                .build();

    }

    public void updateProductById(Long id, ProductRequestDto product){
        Product productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Set<Long> reviewIds = product.getReviewIds();
        Set<Review> reviews=new HashSet<>();
        for(Long reviewId : reviewIds){
            Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("review not found"));
            reviews.add(review);
        }

        Product build = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .reviews(reviews)
                .build();
     productRepository.save(build);

    }


    public void createProduct(ProductRequestDto product){
        Set<Long> reviewIds = product.getReviewIds();
        Set<Review> reviews=new HashSet<>();
        for(Long reviewId : reviewIds){
            Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("review not found"));
            reviews.add(review);
        }


        Product build = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .reviews(reviews)
                .build();

        productRepository.save(build);

    }



    public void deleteProduct(Long id){
        productRepository.findById(id).orElseThrow(()->new RuntimeException("product id not found"));
        productRepository.deleteById(id);
    }

    public void addReview(Long id, ReviewRequestDto reviewRequestDto){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        Review reviewEntity = Review.builder()
                .rating(reviewRequestDto.getRating())
                .comment(reviewRequestDto.getComment())
                .reviewDate(reviewRequestDto.getReviewDate())
                .user(userRepository.findById(reviewRequestDto.getUserId()).orElseThrow(() -> new RuntimeException("user not found")))
                .product(product).build();


        product.getReviews().add(reviewEntity);
        productRepository.save(product);

    }


    public Set<ReviewResponseDto> getReviewsByProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product id not found"));
        Set<Review> reviews = product.getReviews();
        Set<ReviewResponseDto> reviewResponseDtos=new HashSet<>();
        for(Review review :reviews){
            ReviewResponseDto build = ReviewResponseDto.builder()
                    .id(review.getId())
                    .rating(review.getRating())
                    .comment(review.getComment())
                    .reviewDate(review.getReviewDate())
                    .userId(review.getUser().getId())
                    .productId(product.getId()).build();
        }

        return reviewResponseDtos;

    }


}
