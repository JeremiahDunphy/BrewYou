package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JdbcReviewDao implements ReviewDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Review addReview(Review review) {
        String sql = "INSERT INTO reviews (beer_id, user_id, review_text, date_of_review, rating, title) VALUES (?, ?, ?, ?, ?, ?) RETURNING review_id;";
        try {
            int reviewId = jdbcTemplate.queryForObject(sql, Integer.class,
                    review.getBeer_id(),
                    review.getUser_id(),
                    review.getReview_text(),
                    new java.sql.Date(review.getDate_of_review().getTime()),
                    review.getRating(),
                    review.getTitle()
            );

            review.setReview_id(reviewId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return review;
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();

        String sql = "SELECT * FROM reviews";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Review review = mapRowToReview(results);
                reviews.add(review);
            }

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("unable to connect to the server" + e);
        }
        return reviews;
    }

    @Override
    public double getAverageRatingByBeer(int beerId) {
        String sql = "SELECT AVG(rating) FROM reviews WHERE beer_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{beerId}, Double.class );
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<Review> getAllRatingsAndReviewsByBeer(int beerId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews JOIN users AS u ON reviews.user_id = u.user_id WHERE beer_id = ? " +
                "ORDER BY date_of_review ASC";

        try {
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
            while(results.next()) {
                Review review = mapRowToReview(results);
                reviews.add(review);
            }
             } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("unable to connect to the server" + e);
        }
        return reviews;
    }

    public  Review mapRowToReview(SqlRowSet rs) {
        Review review = new Review();
        review.setReview_id(rs.getInt("review_id"));
        review.setBeer_id(rs.getInt("beer_id"));
        review.setUser_id(rs.getInt("user_id"));
        review.setReview_text(rs.getString("review_text"));
        review.setDate_of_review(rs.getDate("date_of_review"));
        review.setRating(rs.getDouble("rating"));
        review.setTitle(rs.getString("title"));
        return review;
    }
}