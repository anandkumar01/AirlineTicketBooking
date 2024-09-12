package com.restapi.airlineticketbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.airlineticketbooking.model.AirlineTicket;
import com.restapi.airlineticketbooking.model.Passenger;

public interface AirlineTicketRepository extends JpaRepository<AirlineTicket, Long> {

    Optional<AirlineTicket> findByFlightNameAndFlightNumberAndPassengerAndSeatNumber(
            String flightName,
            String flightNumber,
            Passenger passenger,
            String seatNumber);
}
