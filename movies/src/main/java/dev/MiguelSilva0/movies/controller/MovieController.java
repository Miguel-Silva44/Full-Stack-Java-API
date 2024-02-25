package dev.MiguelSilva0.movies.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.MiguelSilva0.movies.entity.Movie;
import dev.MiguelSilva0.movies.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
@Tag(name="Movie-api")

public class MovieController {
	@Autowired
	private MovieService movieService;
	
	
	@GetMapping
	@Operation(summary="Pesquisa os filmes", method="GET")
	//@ApiOperation(value = "Pesquisa os filmes", notes = "Retorna todos os filmes")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o get de arquivo"),
    })
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = movieService.allMovies();
		if (movies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	


	@GetMapping("/{imbdId}")
	public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imbdId) {
		return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(imbdId), HttpStatus.OK);
	}

}
