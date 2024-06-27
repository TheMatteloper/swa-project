package swa.semproject.reservationservice.model.dto;

import lombok.Data;
import swa.semproject.reservationservice.enums.ReservationStatus;

import java.time.LocalDate;

@Data
public class ReservationViewDTO {

    private Integer id;

    private Integer roomId;

    private LocalDate date;

    private ReservationStatus status;

}
