package swa.semproject.reservationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.AAA'Z'")
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
