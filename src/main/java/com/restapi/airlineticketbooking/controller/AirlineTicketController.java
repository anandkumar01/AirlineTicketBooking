package com.restapi.airlineticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.airlineticketbooking.model.AirlineTicket;
import com.restapi.airlineticketbooking.repository.AirlineTicketRepository;
import com.restapi.airlineticketbooking.services.AirlineTicketService;

@RestController
@RequestMapping("api/tickets")
public class AirlineTicketController {

    @Autowired
    public AirlineTicketService airlineTicketService;

    @Autowired
    private AirlineTicketRepository airlineTicketRepository;

    @PostMapping("/book")
    public ResponseEntity<?> bookTicket(@RequestBody AirlineTicket ticket) throws Exception {
        try {
            AirlineTicket bookedTicket = airlineTicketService.bookTicket(ticket);
            return new ResponseEntity<>(bookedTicket, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineTicket> getTicket(@PathVariable Long id) {
        AirlineTicket ticket = airlineTicketService.getTicket(id);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelTicket(@PathVariable Long id) {
        try {
            AirlineTicket cancelledTicket = airlineTicketService.cancelTicket(id);
            if (cancelledTicket != null) {
                return new ResponseEntity<>("Ticket cancelled succesfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ticket not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        if (airlineTicketRepository.existsById(id)) {
            airlineTicketRepository.deleteById(id);
            return new ResponseEntity<>("Ticket deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ticket not found", HttpStatus.NOT_FOUND);
        }
    }
}
