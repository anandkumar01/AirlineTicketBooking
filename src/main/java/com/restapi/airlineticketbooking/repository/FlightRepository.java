package com.restapi.airlineticketbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.airlineticketbooking.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFlightNameAndFlightNumber(String flightName, String flightNumber);
}
