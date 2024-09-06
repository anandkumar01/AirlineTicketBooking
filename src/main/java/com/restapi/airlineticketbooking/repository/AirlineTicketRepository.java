package com.restapi.airlineticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.airlineticketbooking.model.AirlineTicket;

public interface AirlineTicketRepository extends JpaRepository<AirlineTicket, Long> {

}
