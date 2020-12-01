package com.app.agritech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.agritech.entity.Movie;
import com.app.agritech.entity.MovieRating;
import com.app.agritech.entity.MovieShow;
import com.app.agritech.entity.PersonalDetails;
import com.app.agritech.entity.Ticket;
import com.app.agritech.entity.UserDetails;
import com.app.agritech.repository.MovieRatingRepository;
import com.app.agritech.repository.MovieRepository;
import com.app.agritech.repository.MovieShowRepository;
import com.app.agritech.repository.PersonRepository;
import com.app.agritech.repository.TicketRepository;
import com.app.agritech.repository.UserDetailsRepository;
import com.app.agritech.service.MovieTicketService;

@Service
public class MovieTicketServiceImpl implements MovieTicketService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieShowRepository movieShowRepository;

	@Autowired
	MovieRatingRepository movieRatingRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	PersonRepository personRepository;

	@Override
	public Movie saveOrUpdateMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public void deleteMovies(List<Long> movieIds) {
		for (Long id : movieIds) {
			movieRepository.deleteById(id);
		}
	}

	@Override
	public List<Movie> searchMovies(String movieName) {
		if (movieName == null || movieName.isEmpty())
			return (List<Movie>) movieRepository.findAll();
		return movieRepository.findAllByNameContains(movieName);
	}

	@Override
	public UserDetails getUserDetails(String userId, String password) {
		return userDetailsRepository.findByEmailIdAndPassword(userId, password);
	}

	@Override
	public UserDetails saveUserDetails(UserDetails user) {
		return userDetailsRepository.save(user);
	}

	@Override
	public PersonalDetails saveProfile(PersonalDetails personDetails) {
		return null;
	}

	@Override
	public Ticket saveOrUpdateTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public MovieRating saveOrUpdateMovieRating(MovieRating movieRating) {
		return movieRatingRepository.save(movieRating);
	}

	@Override
	public MovieShow getMovieShow(Long showId) {
		return movieShowRepository.findById(showId).get();
	}

	@Override
	public Movie getMovie(Long movieId) {
		return movieRepository.findById(movieId).get();
	}

	@Override
	public Ticket getMovieTicket(Long ticketId) {
		return ticketRepository.findById(ticketId).get();
	}

	@Override
	public List<MovieRating> getMovieRatings(Long userId) {
		if (userId != null)
			return movieRatingRepository.findAllByUserId(userId);
		else
			return (List<MovieRating>) movieRatingRepository.findAll();
	}

	@Override
	public MovieShow saveMovieShow(MovieShow movieShow) {
		return movieShowRepository.save(movieShow);
	}

	@Override
	public List<MovieShow> getMovieShows(Long movieId) {
		return movieShowRepository.findAllByMovieId(movieId);
	}

	@Override
	public void deleteMovieRating(List<Long> ids) {
		for (Long id : ids) {
			movieRatingRepository.deleteById(id);
		}
	}

	@Override
	public UserDetails getUserDetails(Long userId) {
		return userDetailsRepository.findAllById(userId);
	}

	@Override
	public void deleteMovieTicket(Long ticketId) {
		ticketRepository.deleteById(ticketId);
	}

	@Override
	public List<Ticket> getAllMovieTickets() {
		return (List<Ticket>) ticketRepository.findAll();
	}

	@Override
	public List<UserDetails> getAllUserDetails() {
		return (List<UserDetails>) userDetailsRepository.findAll();
	}
}
