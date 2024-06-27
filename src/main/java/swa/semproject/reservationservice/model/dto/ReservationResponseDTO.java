package swa.semproject.reservationservice.model.dto;

import lombok.Data;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationResponseDTO {

    private Integer id;

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    private ReservationStatus status;

    public ReservationResponseDTO(Reservation r) {
        this.id = r.getId();
        this.userId = r.getUserId();
        this.roomId = r.getRoomId();
        this.date = r.getDate();
        this.timeFrom = r.getTimeFrom();
        this.timeTo = r.getTimeTo();
        this.status = r.getStatus();
    }

}
