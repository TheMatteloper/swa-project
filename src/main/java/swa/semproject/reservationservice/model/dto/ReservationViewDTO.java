package swa.semproject.reservationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationViewDTO {

    private Integer id;

    private Integer roomId;

    private LocalDateTime timeFrom;

    private ReservationStatus status;

    public ReservationViewDTO(Reservation r) {
        this.id = r.getId();
        this.roomId = r.getRoomId();
        this.timeFrom = r.getTimeFrom();
        this.status = r.getStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationViewDTO that = (ReservationViewDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(roomId, that.roomId) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, status);
    }

}
