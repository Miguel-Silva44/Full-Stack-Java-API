package dev.MiguelSilva0.movies.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.MiguelSilva0.movies.entity.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
	Optional<Movie> findMovieByImdbId(String imdbId);
		
	
}
