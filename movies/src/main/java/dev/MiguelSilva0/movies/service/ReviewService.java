package dev.MiguelSilva0.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import dev.MiguelSilva0.movies.entity.Movie;
import dev.MiguelSilva0.movies.entity.Review;
import dev.MiguelSilva0.movies.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	public Review createReview(String reviewBody, String imbdId) {
		Review review = reviewRepository.insert(new Review(reviewBody));
		
		mongoTemplate.update(Movie.class)
							.matching(Criteria.where("imbdId").is(imbdId))
							.apply(new Update().push("reviewIds").value(review))
							.first();
								
		return review;
	}
}
