package com.restapi.airlineticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.airlineticketbooking.model.AirlineTicket;
import com.restapi.airlineticketbooking.services.AirlineTicketService;

@RestController
@RequestMapping("api/tickets")
public class AirlineTicketController {
    
    @Autowired
    public AirlineTicketService airlineTicketService;

    @PostMapping("/book")
    public ResponseEntity<AirlineTicket> bookTicket(@RequestBody AirlineTicket ticket) {
        AirlineTicket bookedTicket = airlineTicketService.bookTicket(ticket);
        return new ResponseEntity<>(bookedTicket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineTicket> getTicket(@PathVariable Long id) {
        AirlineTicket ticket = airlineTicketService.getTicket(id);
        if(ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
