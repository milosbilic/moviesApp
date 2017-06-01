package jwd.movies.web.dto;

public class MovieDTO {
	private Long id;
	private String title;
	private int year;
	private double rate;
	private GenreDTO genre;
	
	public MovieDTO() {
		super();
	}
	
	public MovieDTO(Long id, String title, int year, double rate, GenreDTO genre) {
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
	public GenreDTO getGenre() {
		return genre;
	}
	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}
}