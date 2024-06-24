package swa.semproject.reservationservice.model;

import lombok.*;
import swa.semproject.reservationservice.enums.ReservationStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    @Enumerated
    private ReservationStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(timeFrom, that.timeFrom) &&
                Objects.equals(timeTo, that.timeTo) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, roomId, date, timeFrom, timeTo, status);
    }
}

