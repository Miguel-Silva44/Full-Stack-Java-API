package dev.MiguelSilva0.movies.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.MiguelSilva0.movies.entity.Movie;
import dev.MiguelSilva0.movies.repository.MovieRepository;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	
	public List<Movie> allMovies() {
		return movieRepository.findAll();
	}
	public Optional<Movie> singleMovie(String imbdId) {
		return movieRepository.findMovieByImdbId(imbdId);
	}
}