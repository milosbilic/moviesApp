package jwd.movies.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.movies.model.Genre;
import jwd.movies.service.GenreService;
import jwd.movies.support.GenreToGenreDTO;
import jwd.movies.web.dto.GenreDTO;

@RestController
@RequestMapping(value="/api/genres")
public class ApiGenreController {
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	GenreToGenreDTO genreToGenreDTO;

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<GenreDTO>> getAllMovies(){
		
		List<Genre> genres = genreService.findAll();
		
		if(genres == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<GenreDTO>>(genreToGenreDTO.convert(genres), HttpStatus.OK);
		}
	}
}
