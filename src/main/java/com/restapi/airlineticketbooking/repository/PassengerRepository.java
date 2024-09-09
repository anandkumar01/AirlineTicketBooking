package com.restapi.airlineticketbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.airlineticketbooking.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByNameAndEmailAndPhone(String name, String email, String phone);
}
