package jwd.movies.support;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.movies.model.Genre;
import jwd.movies.model.Movie;
import jwd.movies.service.GenreService;
import jwd.movies.service.MovieService;

@Component
public class TestData {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	GenreService genreService;
	
	@PostConstruct
	public void init(){
		for(int i=1; i<=5; i++){
			Genre genre = new Genre();
			genre.setName("Genre"+i);
			List<Movie> movies = new ArrayList<>();
			
			for(int j=1; j<=5; j++){
				int random = (int)(Math.random() * 10);
				Movie movie = new Movie("Movie"+random,2000 + random,random,genre);
				movies.add(movie);
			}
			
			genre.setMovies(movies);
			genreService.save(genre);
			
			for(Movie m : movies){
				movieService.save(m);
			}
		}
		
//		Genre genre = new Genre("Genre");
//		genreService.save(genre);
//		Movie movie = new Movie("Movie1", 2015, 7.3, genre);
//		
//		movieService.save(movie);
	}
}
