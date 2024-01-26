package com.techelevator.dao;

import com.techelevator.model.Beer;
import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {
    Review addReview(Review review);

    double getAverageRatingByBeer(int beerId);

    List <Review> getAllRatingsAndReviewsByBeer(int beer_Id);

    List<Review> getAllReviews();
    }



