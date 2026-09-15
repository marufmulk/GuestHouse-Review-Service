package org.nackademin.reviewservice.service;

import lombok.RequiredArgsConstructor;
import org.nackademin.reviewservice.client.BookingClient;
import org.nackademin.reviewservice.dto.ReviewDto;
import org.nackademin.reviewservice.entity.Review;
import org.nackademin.reviewservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingClient bookingClient;

    private ReviewDto toDto(Review review) {
        return new ReviewDto(review.getId(), review.getCustomerId(), review.getRoomId(), review.getRating(), review.getComment());
    }

    private Review toEntity(ReviewDto dto) {
        return new Review(dto.getId(), dto.getCustomerId(), dto.getRoomId(), dto.getRating(), dto.getComment());
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream().map(this::toDto).toList();
    }


    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {
        if (!bookingClient.customerHasBookedRoom(reviewDto.getCustomerId(), reviewDto.getRoomId())) {
            throw new IllegalStateException("Kunden har inte bokat detta rum " + "och kan inte lämna en recension");
        }
        Review saved = reviewRepository.save(toEntity(reviewDto));
        return toDto(saved);
    }

    @Override
    public List<ReviewDto> getReviewsByRoom(Long roomId) {
        return reviewRepository.findByRoomId(roomId).stream().map(this::toDto).toList();
    }

    @Override
    public List<ReviewDto> getReviewsByCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId).stream().map(this::toDto).toList();
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Recension hittades inte"));
        reviewRepository.deleteById(id);
    }
}