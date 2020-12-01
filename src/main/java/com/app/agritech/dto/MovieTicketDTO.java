package com.app.agritech.dto;

import com.app.agritech.entity.Movie;
import com.app.agritech.entity.MovieShow;
import com.app.agritech.entity.Ticket;

public class MovieTicketDTO {

	Movie movie;

	MovieShow show;

	Ticket ticket;

	public Integer getPrice() {
		return show.getTicketPrice() * ticket.getSeatNos().split(",").length;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public MovieShow getShow() {
		return show;
	}

	public void setShow(MovieShow show) {
		this.show = show;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
