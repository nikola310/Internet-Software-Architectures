package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
