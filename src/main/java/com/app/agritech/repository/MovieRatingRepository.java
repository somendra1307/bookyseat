package com.app.agritech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.agritech.entity.MovieRating;

@Repository
public interface MovieRatingRepository extends CrudRepository<MovieRating, Long> {

	MovieRating findAllById(Long id);

	List<MovieRating> findAllByUserId(Long userId);

}
