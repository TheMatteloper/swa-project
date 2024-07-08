package swa.semproject.reservationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationViewDTO {

    private Integer id;

    private Integer roomId;

    private LocalDate date;

    private ReservationStatus status;

    public ReservationViewDTO(Reservation r) {
        this.id = r.getId();
        this.roomId = r.getRoomId();
        this.date = r.getDate();
        this.status = r.getStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationViewDTO that = (ReservationViewDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(roomId, that.roomId) && Objects.equals(date, that.date) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, date, status);
    }

}
