package com.app.agritech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.agritech.entity.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

	List<Ticket> findAllByUserId(Long userId);

}
