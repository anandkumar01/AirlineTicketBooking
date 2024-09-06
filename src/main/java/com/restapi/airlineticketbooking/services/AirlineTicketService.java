package com.restapi.airlineticketbooking.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.airlineticketbooking.model.AirlineTicket;
import com.restapi.airlineticketbooking.repository.AirlineTicketRepository;

@Service
public class AirlineTicketService {

    @Autowired
    private AirlineTicketRepository airlineTicketRepository;

    public AirlineTicket bookTicket(AirlineTicket ticket) throws Exception {
        LocalDateTime departureTime = ticket.getDepartureTime();
        if (departureTime.isAfter(LocalDateTime.now())) {
            ticket.setBookingTime(LocalDateTime.now());
            return airlineTicketRepository.save(ticket);
        } else {
            throw new IllegalArgumentException("Departure time cannot be earlier than the current time.");
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
