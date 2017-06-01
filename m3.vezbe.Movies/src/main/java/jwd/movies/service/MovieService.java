package jwd.movies.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.movies.model.Movie;

public interface MovieService{
	
	public Page<Movie> findAll(int page);
	
	public Movie findOne(Long id);
	
	public List<Movie> filterMovies(String title, double rating, int yearMin, int yearMax, Long idMin, Long idMax);
	
	public Movie save(Movie movie);
	
	public void delete(Long id);
}
