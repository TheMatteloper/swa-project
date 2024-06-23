package swa.semproject.reservationservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;
import swa.semproject.reservationservice.service.ReservationService;

import java.net.URI;
import java.util.Set;


@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/my-reservations")
    public ResponseEntity<Set<ReservationViewDTO>> getOwnedReservations() {
        logger.info("GET request - reservation/my-reservations");
        return ResponseEntity.ok(reservationService.getAllOwnedReservations());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Integer reservationId) {
        logger.info("GET request - reservation/{id}");
        return ResponseEntity.ok(reservationService.getReservationById(reservationId));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Void> createReservation(@RequestBody Reservation reservation) throws Exception {
        logger.info("POST request - reservation/new");
        reservationService.createReservation(reservation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservation.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable("id") Integer reservationId) {
        logger.info("DELETE request - reservation/{id}");
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }


}
