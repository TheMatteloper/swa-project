package swa.semproject.reservationservice.model;

import jakarta.persistence.*;
import lombok.*;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    @Enumerated
    private ReservationStatus status;

    public Reservation() {}

    public Reservation(ReservationRequestDTO r) {
        this.id = null;
        this.userId = r.getUserId();
        this.roomId = r.getRoomId();
        this.date = r.getDate();
        this.timeFrom = r.getTimeFrom();
        this.timeTo = r.getTimeTo();
        this.status = r.getStatus();
    }

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

