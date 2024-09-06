package com.restapi.airlineticketbooking.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restapi.airlineticketbooking.model.Flight;
import com.restapi.airlineticketbooking.repository.FlightRepository;

import jakarta.annotation.PostConstruct;

@Component
public class FlightDataInitializer {

    @Autowired
    private FlightRepository flightRepository;

    @PostConstruct
    public void init() {

        flightRepository.deleteAll();

        List<Flight> flights = Arrays.asList(
                new Flight("Air India", "AI123"),
                new Flight("Vistara", "VS456"),
                new Flight("IndiGo", "IG789"),
                new Flight("SpiceJet", "SJ101"),
                new Flight("Air India Express", "AIX202"),
                new Flight("Akasa Air", "AA303"));

        flightRepository.saveAll(flights);
    }
}
