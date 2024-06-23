package swa.semproject.reservationservice.model;

import lombok.*;
import swa.semproject.reservationservice.enums.ReservationStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    private int totalPrice;

    private Time timeFrom;

    private Time timeTo;

    @Enumerated
    private ReservationStatus status;

}

