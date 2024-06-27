package swa.semproject.reservationservice.model.dto;

import lombok.Data;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationRequestDTO {

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    private ReservationStatus status;

    public ReservationRequestDTO(Reservation r) {
        this.userId = r.getUserId();
        this.roomId = r.getRoomId();
        this.date = r.getDate();
        this.timeFrom = r.getTimeFrom();
        this.timeTo = r.getTimeTo();
        this.status = r.getStatus();
    }

}
