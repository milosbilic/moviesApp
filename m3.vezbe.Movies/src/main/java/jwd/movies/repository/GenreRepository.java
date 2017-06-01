package jwd.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.movies.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{

}
