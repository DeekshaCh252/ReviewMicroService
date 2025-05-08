package com.example.reviewMicroService.reviews;

import java.util.List;

public interface ReviewsService {
    List<Reviews> getAllReviews(Long companyId);

    boolean postReview(Long companyId, Reviews reviews);

    Reviews getReview(Long id);

    boolean updateReview(Long id, Reviews review);

    boolean deleteReview(Long id);
}
