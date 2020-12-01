package com.app.agritech.dto;

import com.app.agritech.entity.Movie;
import com.app.agritech.entity.MovieRating;
import com.app.agritech.entity.UserDetails;

public class MovieRatingDTO {

	Movie movie;

	MovieRating rating;

	UserDetails user;

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public MovieRating getRating() {
		return rating;
	}

	public void setRating(MovieRating rating) {
		this.rating = rating;
	}

}
