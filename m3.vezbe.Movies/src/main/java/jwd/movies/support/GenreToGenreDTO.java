package jwd.movies.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.movies.model.Genre;
import jwd.movies.web.dto.GenreDTO;

@Component
public class GenreToGenreDTO implements Converter<Genre, GenreDTO>{

	@Override
	public GenreDTO convert(Genre genre) {
		GenreDTO dto = new GenreDTO(genre.getId(), genre.getName());
		return dto;
	}
	
	public List<GenreDTO> convert(List<Genre> genres) {
		List<GenreDTO> genresDto = new ArrayList<>();
		
		for(Genre genre : genres){
			genresDto.add(convert(genre));
		}
		return genresDto;
	}
}
