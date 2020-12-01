package com.app.agritech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.agritech.entity.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

	Movie findAllById(Long id);

	List<Movie> findAllByNameContains(String name);

}
