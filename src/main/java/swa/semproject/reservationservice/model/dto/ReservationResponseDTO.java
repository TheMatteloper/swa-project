package swa.semproject.reservationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponseDTO {

    private Integer id;

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime timeFrom;

    @JsonFormat(pattern="HH:mm:ss")
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
