package jwd.movies.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.movies.model.Movie;
import jwd.movies.web.dto.MovieDTO;

@Component
public class MovieDtoToMovie implements Converter<MovieDTO, Movie>{

	@Autowired
	GenreDtoToGenre genreDtoToGenre;
	
	@Override
	public Movie convert(MovieDTO dto) {
		Movie movie = new Movie(dto.getId(), dto.getTitle(), dto.getYear(), dto.getRate(), genreDtoToGenre.convert(dto.getGenre()));
		return movie;
	}

}
