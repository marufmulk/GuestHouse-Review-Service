package org.nackademin.reviewservice.repository;

import org.nackademin.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRoomId(Long roomId);

    List<Review> findByCustomerId(Long customerId);
}