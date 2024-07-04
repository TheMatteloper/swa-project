package cvut.swa.integrationtest.dto;

import cvut.swa.integrationtest.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class ReservationViewDTO {

    private Integer id;

    private Integer roomId;

    private LocalDate date;

    private ReservationStatus status;


    public ReservationViewDTO(Integer id, Integer roomId, LocalDate date, ReservationStatus status) {
        this.id = id;
        this.roomId = roomId;
        this.date = date;
        this.status = status;
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
