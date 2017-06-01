package jwd.movies.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genre {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy="genre")
	private List<Movie> movies = new ArrayList<>();
	
	public Genre() {
		super();
	}
	
	public Genre(String name) {
		super();
		this.name = name;
	}
	
	public Genre(String name, List<Movie> movies) {
		super();
		this.name = name;
		this.movies = movies;
	}
	
	public Genre(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Genre(Long id, String name, List<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.movies = movies;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Movie> getMovies() {
		return movies;
	}
	
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
