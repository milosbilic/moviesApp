package jwd.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import jwd.movies.support.TestData;

@SpringBootApplication
public class MoviesApp extends SpringBootServletInitializer{

	@Autowired
	private TestData testData;
	
	public static void main(String[] args) {
		SpringApplication.run(MoviesApp.class, args);
	}

}
