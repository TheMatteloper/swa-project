package swa.semproject.reservationservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;
import swa.semproject.reservationservice.model.dto.ReservationResponseDTO;
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

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Set<ReservationViewDTO>> getOwnedReservations(@PathVariable("id") Integer userId) {
        logger.info("GET request - reservation/user/{id}");
        return ResponseEntity.ok(reservationService.getAllOwnedReservations(userId));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable("id") Integer reservationId) {
        logger.info("GET request - reservation/{id}");
        return ResponseEntity.ok(reservationService.getReservationDTOById(reservationId));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Void> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) throws Exception {
        logger.info("POST request - reservation/new");
        Integer resId = reservationService.createReservation(reservationRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resId)
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
