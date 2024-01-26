package com.techelevator.controller;

import com.techelevator.dao.JdbcReviewDao;
import com.techelevator.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final JdbcReviewDao jdbcReviewDao;

    @Autowired
    public ReviewController(JdbcReviewDao jdbcReviewDao) {
        this.jdbcReviewDao = jdbcReviewDao;
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review newReview = jdbcReviewDao.addReview(review);
        return ResponseEntity.ok(newReview);
    }

    @GetMapping
    public ResponseEntity<List <Review>> getAllReviews() {
        List<Review> reviews = jdbcReviewDao.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/average/{beerId}")
    public ResponseEntity<Double> getAverageRatingByBeer(@PathVariable int beerId) {
        Double averageRating = jdbcReviewDao.getAverageRatingByBeer(beerId);
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/beer/{beerId}")
    public ResponseEntity<List<Review>> getAllRatingsAndReviewsByBeer(@PathVariable int beerId) {
        List<Review> reviews = jdbcReviewDao.getAllRatingsAndReviewsByBeer(beerId);
        return ResponseEntity.ok(reviews);
    }
}
