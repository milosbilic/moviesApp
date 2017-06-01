package jwd.movies.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.movies.model.Movie;
import jwd.movies.service.MovieService;
import jwd.movies.support.MovieDtoToMovie;
import jwd.movies.support.MovieToMovieDTO;
import jwd.movies.web.dto.MovieDTO;

@RestController
@RequestMapping(value="/api/movies")
public class ApiMovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	MovieToMovieDTO movieToMovieDTO;
	
	@Autowired
	MovieDtoToMovie movieDtoToMovie;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<MovieDTO>> getAllMovies(
			@RequestParam(value="page", defaultValue="0") int page){
		
		Page<Movie> movies = movieService.findAll(page);
		
		if(movies == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", movies.getTotalPages() + "");
			return new ResponseEntity<List<MovieDTO>>(movieToMovieDTO.convert(movies.getContent()), headers, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/filterMovies")
	ResponseEntity<List<MovieDTO>> filterMovies(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="filterTitle", defaultValue="") String filterTitle,
			@RequestParam(value="filterYear", defaultValue="0") int filterYear,
			@RequestParam(value="filterRating", defaultValue="0") double filterRating,
			@RequestParam(value="filterGenre", defaultValue="-1") Long filterGenre){
		
		System.out.println(filterTitle + ", " + filterYear + ",  " + filterRating + ", " + filterGenre);
		
		int yearMin = filterYear;
		int yearMax = filterYear;
		
		Long idMin = filterGenre;
		Long idMax = filterGenre;
		
		if(filterYear == 0){
			yearMax = 3000;
		}
		
		if(filterGenre == -1){
			idMax = 5000000L;
		}
		
		List<Movie> filteredMovies = movieService.filterMovies(filterTitle, filterRating, yearMin, yearMax, idMin, idMax);
		
		return new ResponseEntity<List<MovieDTO>>(movieToMovieDTO.convert(filteredMovies), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	ResponseEntity<MovieDTO> getMovie(@PathVariable Long id){
		
		if(id == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		Movie movie = movieService.findOne(id);
		
		if(movie == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<MovieDTO>(movieToMovieDTO.convert(movie), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDTO){
		
		Movie movie = movieService.save(movieDtoToMovie.convert(movieDTO));
		
		if(movie == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<MovieDTO>(movieToMovieDTO.convert(movie), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	ResponseEntity<MovieDTO> addMovie(
			@RequestBody MovieDTO movieDTO,
			@PathVariable(value="id") Long id){
		
		if(!movieDTO.getId().equals(id))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Movie movie = movieService.save(movieDtoToMovie.convert(movieDTO));
		
		if(movie == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<MovieDTO>(movieToMovieDTO.convert(movie), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	ResponseEntity<List<MovieDTO>> deleteMovie(@PathVariable Long id){
		
		movieService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
