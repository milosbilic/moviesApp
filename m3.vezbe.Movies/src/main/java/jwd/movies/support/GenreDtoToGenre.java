package jwd.movies.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.movies.model.Genre;
import jwd.movies.web.dto.GenreDTO;

@Component
public class GenreDtoToGenre implements Converter<GenreDTO, Genre>{

	@Override
	public Genre convert(GenreDTO dto) {		
		Genre genre = new Genre(dto.getId(), dto.getName());
		return genre;
	}

}
