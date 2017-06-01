package jwd.movies.service;

import java.util.List;

import jwd.movies.model.Genre;

public interface GenreService {
	public List<Genre> findAll();
	
	public Genre save(Genre genre);
}
