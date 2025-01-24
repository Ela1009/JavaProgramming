package com.bookRepo.controller;

import com.bookRepo.model.Reservation;
import com.bookRepo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public void CreateReservation(@RequestBody Reservation reservation) {
        reservationService.createReservation(reservation);

    }

    @PutMapping(path = "/{Id}/fulfill")
    public void FulfillReservation(@PathVariable("Id") Long Id){
        reservationService.fulfillReservation(Id);
    }

}