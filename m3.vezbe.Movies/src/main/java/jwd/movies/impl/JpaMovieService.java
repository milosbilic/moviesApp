package jwd.movies.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.movies.model.Movie;
import jwd.movies.repository.MovieRepository;
import jwd.movies.service.MovieService;

@Service
@Transactional
public class JpaMovieService implements MovieService{
	
	@Autowired
	MovieRepository movieRepository;

	@Override
	public Page<Movie> findAll(int page) {
		return movieRepository.findAll(new PageRequest(page, 10));
	}
	
	@Override
	public Movie findOne(Long id) {
		return movieRepository.findOne(id);
	}

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public void delete(Long id) {
		movieRepository.delete(id);
	}

	@Override
	public List<Movie> filterMovies(String title, double rating, int yearMin, int yearMax, Long idMin, Long idMax) {
		return movieRepository.filterMovies(title, rating, yearMin, yearMax, idMin, idMax);
	}
	

}
