package com.example.reviewMicroService.reviews.impl;


import com.example.reviewMicroService.reviews.Reviews;
import com.example.reviewMicroService.reviews.ReviewsRepository;
import com.example.reviewMicroService.reviews.ReviewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private ReviewsRepository reviewsRepository;

    public ReviewsServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public List<Reviews> getAllReviews(Long companyId) {
        List<Reviews> reviews = reviewsRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean postReview(Long companyId, Reviews reviews) {
        if(companyId != null && reviews != null) {
            reviews.setCompanyId(companyId);
            reviewsRepository.save(reviews);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Reviews getReview(Long id) {
        Reviews reviews = reviewsRepository.findById(id).orElse(null);
        return reviews;
    }

    @Override
    public boolean updateReview(Long id, Reviews review) {
        Reviews review1 = reviewsRepository.findById(id).orElse(null);
        if(review1 != null) {
            review1.setTitle(review.getTitle());
            review1.setDescription(review.getDescription());
            review1.setRating(review.getRating());
            reviewsRepository.save(review1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long id) {
        Reviews review1 = reviewsRepository.findById(id).orElse(null);
        if(review1 != null){
            reviewsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
