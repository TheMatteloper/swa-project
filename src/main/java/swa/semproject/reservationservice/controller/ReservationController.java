package swa.semproject.reservationservice.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;
import swa.semproject.reservationservice.model.dto.ReservationResponseDTO;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;
import swa.semproject.reservationservice.service.ReservationService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservations retrieved"),
            @ApiResponse(responseCode = "400", description = "User not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content())
    })
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<ReservationViewDTO>> getOwnedReservations(@PathVariable("userId") Integer userId) throws Exception {
        logger.info("GET request - reservation/user/{userId}");
        return ResponseEntity.ok(reservationService.getAllOwnedReservations(userId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation retrieved"),
            @ApiResponse(responseCode = "400", description = "Reservation not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content())
    })

    @GetMapping(value = "/{resId}/user/{userId}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable("resId") Integer reservationId,
                                                                     @PathVariable("userId") Integer userId) throws Exception {
        logger.info("GET request - reservation/{resId}/user/{userId}");
        return ResponseEntity.ok(reservationService.getReservationDTOById(reservationId, userId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Reservation could not be created", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content())
    })
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation successfully canceled"),
            @ApiResponse(responseCode = "400", description = "Reservation could not be canceled", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content())
    })
    @DeleteMapping (value = "/{resId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable("resId") Integer reservationId) {
        logger.info("DELETE request - reservation/{resId}");
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }

}
