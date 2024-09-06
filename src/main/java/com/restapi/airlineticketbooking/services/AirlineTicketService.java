package com.restapi.airlineticketbooking.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.airlineticketbooking.model.AirlineTicket;
import com.restapi.airlineticketbooking.model.Flight;
import com.restapi.airlineticketbooking.repository.AirlineTicketRepository;
import com.restapi.airlineticketbooking.repository.FlightRepository;

@Service
public class AirlineTicketService {

    @Autowired
    private AirlineTicketRepository airlineTicketRepository;

    @Autowired
    private FlightRepository flightRepository;

    public AirlineTicket bookTicket(AirlineTicket ticket) throws Exception {
        // Validate departure time
        LocalDateTime departureTime = ticket.getDepartureTime();
        if (!departureTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Departure time cannot be earlier than the current time.");
        }

        // Check if the flight exists with the same flight name and flight number
        Optional<Flight> flight = flightRepository.findByFlightNameAndFlightNumber(
                ticket.getFlightName(), ticket.getFlightNumber());

        if (flight.isPresent()) {
            ticket.setBookingTime(LocalDateTime.now());
            return airlineTicketRepository.save(ticket);
        } else {
            throw new Exception("No flight found with the specified name and number.");
        }
    }

    public AirlineTicket getTicket(Long id) {
        return airlineTicketRepository.findById(id).orElse(null);
    }

    public AirlineTicket cancelTicket(Long id) {
        Optional<AirlineTicket> optionalTicket = airlineTicketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            AirlineTicket ticket = optionalTicket.get();
            if (ticket.isCancelled()) {
                throw new IllegalStateException("Ticket is already cancelled.");
            }
            if (ticket.getDepartureTime().isBefore(LocalDateTime.now())) {
                throw new IllegalStateException("Can not cancel a ticket after departure.");
            }
            ticket.setCancelled(true);
            return airlineTicketRepository.save(ticket);
        } else {
            return null;
        }
    }
}
