package com.app.agritech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.agritech.entity.MovieShow;

@Repository
public interface MovieShowRepository extends CrudRepository<MovieShow, Long> {

	List<MovieShow> findAllByMovieId(Long movieId);

}
