package org.nackademin.reviewservice.service;

import org.nackademin.reviewservice.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getAllReviews();

    ReviewDto saveReview(ReviewDto reviewDto);

    List<ReviewDto> getReviewsByRoom(Long roomId);

    List<ReviewDto> getReviewsByCustomer(Long customerId);

    void deleteReview(Long id);
}
