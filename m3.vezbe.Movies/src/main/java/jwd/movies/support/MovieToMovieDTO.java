package jwd.movies.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.movies.model.Movie;
import jwd.movies.web.dto.MovieDTO;

@Component
public class MovieToMovieDTO implements Converter<Movie, MovieDTO>{

	@Autowired
	GenreToGenreDTO genreToGenreDTO;
	
	@Override
	public MovieDTO convert(Movie movie) {
		MovieDTO dto = new MovieDTO(movie.getId(), movie.getTitle(), movie.getYear(), movie.getRate(), genreToGenreDTO.convert(movie.getGenre()));
		return dto;
	}

	public List<MovieDTO> convert(List<Movie> movies){
		List<MovieDTO> moviesDTO = new ArrayList<>();
		
		for(Movie movie : movies){
			moviesDTO.add(convert(movie));
		}
		
		return moviesDTO;
	}
}
