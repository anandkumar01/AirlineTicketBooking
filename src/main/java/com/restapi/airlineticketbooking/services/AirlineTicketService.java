package com.restapi.airlineticketbooking.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.airlineticketbooking.model.AirlineTicket;
import com.restapi.airlineticketbooking.repository.AirlineTicketRepository;

@Service
public class AirlineTicketService {
    
    @Autowired
    private AirlineTicketRepository airlineTicketRepository;

    public AirlineTicket bookTicket(AirlineTicket ticket) {
        ticket.setBookingTime(LocalDateTime.now());
        return airlineTicketRepository.save(ticket);
    }

    public AirlineTicket getTicket(Long id){
        return airlineTicketRepository.findById(id).orElse(null);
    }
}
