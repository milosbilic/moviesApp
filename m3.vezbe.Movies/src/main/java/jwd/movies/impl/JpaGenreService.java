package jwd.movies.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.movies.model.Genre;
import jwd.movies.repository.GenreRepository;
import jwd.movies.service.GenreService;

@Service
public class JpaGenreService implements GenreService{

	@Autowired
	GenreRepository genreRepository;
	
	@Override
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

	@Override
	public Genre save(Genre genre) {
		return genreRepository.save(genre);
	}
}
