package com.app.agritech.service;

import java.util.List;

import com.app.agritech.entity.Movie;
import com.app.agritech.entity.MovieRating;
import com.app.agritech.entity.MovieShow;
import com.app.agritech.entity.PersonalDetails;
import com.app.agritech.entity.Ticket;
import com.app.agritech.entity.UserDetails;

public interface MovieTicketService {

	Movie saveOrUpdateMovie(Movie movie);

	Ticket saveOrUpdateTicket(Ticket ticket);

	MovieRating saveOrUpdateMovieRating(MovieRating movieRating);

	void deleteMovies(List<Long> movieIds);

	List<Movie> searchMovies(String movieName);

	UserDetails getUserDetails(String userId, String password);

	UserDetails saveUserDetails(UserDetails user);

	PersonalDetails saveProfile(PersonalDetails personDetails);

	MovieShow getMovieShow(Long showId);

	Movie getMovie(Long movieId);

	Ticket getMovieTicket(Long ticketId);

	void deleteMovieTicket(Long ticketId);

	List<Ticket> getAllMovieTickets();

	List<MovieRating> getMovieRatings(Long userId);

	MovieShow saveMovieShow(MovieShow movieShow);

	List<MovieShow> getMovieShows(Long movieId);

	void deleteMovieRating(List<Long> ids);

	void deleteUser(Long userId);

	UserDetails getUserDetails(Long userId);

	List<UserDetails> getAllUserDetails();

}
