package com.example.reviewMicroService.reviews;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping
    public ResponseEntity<List<Reviews>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewsService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Reviews reviews) {
        boolean status = reviewsService.postReview(companyId, reviews);
        if (status) return new ResponseEntity<>("Successfully posted Review", HttpStatus.CREATED);
        return new ResponseEntity<>("Company Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reviews> getReview(@PathVariable Long id) {
        Reviews review = reviewsService.getReview(id);
        if (review != null) return new ResponseEntity<>(review, HttpStatus.FOUND);
        return new ResponseEntity<>(review, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody Reviews review) {
        boolean updated = reviewsService.updateReview(id, review);
        if (updated) return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
        return new ResponseEntity<>("Review Not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id){
        boolean deleted = reviewsService.deleteReview(id);
        if(deleted) return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);
    }
}
