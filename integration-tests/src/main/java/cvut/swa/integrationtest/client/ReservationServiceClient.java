package cvut.swa.integrationtest.client;

import cvut.swa.integrationtest.dto.ReservationRequestDTO;
import cvut.swa.integrationtest.dto.ReservationResponseDTO;
import cvut.swa.integrationtest.dto.ReservationViewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "reservation-service", url = "http://docker:8235/reservation")
public interface ReservationServiceClient {

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ReservationViewDTO>> getOwnedReservations(@PathVariable("userId") Integer userId);

    @GetMapping(value = "/{resId}/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable("resId") Integer reservationId,
                                              @PathVariable("userId") Integer userId);

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO);

    @DeleteMapping(value = "/{resId}")
    void cancelReservation(@PathVariable("resId") Integer reservationId);

}
