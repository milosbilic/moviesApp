package jwd.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jwd.movies.model.Movie;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>{
	@Query("SELECT x FROM Movie x where x.title LIKE ?1% AND x.rate >= ?2 AND x.year >= ?3 AND x.year <= ?4 AND x.genre.id >= ?5 AND x.genre.id <= ?6")
	List<Movie> filterMovies(String title, double rating, int yearMin, int yearMax, Long idMin, Long idMax);
}