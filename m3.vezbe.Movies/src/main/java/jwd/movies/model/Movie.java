package jwd.movies.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movie {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private int year;
	private double rate;
	@ManyToOne(fetch=FetchType.LAZY)
	private Genre genre;
	
	public Movie() {
		super();
	}

	public Movie(String title, int year, double rate, Genre genre) {
		super();
		this.title = title;
		this.year = year;
		this.rate = rate;
		this.genre = genre;
	}

	public Movie(Long id, String title, int year, double rate, Genre genre) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.rate = rate;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
}
