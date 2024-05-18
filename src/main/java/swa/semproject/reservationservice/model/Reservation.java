package swa.semproject.reservationservice.model;

//import swa.semproject.reservationservice.enums.ReservationStatus;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private Integer userId;

    private Date date;

    private int totalPrice;

    private Time timeFrom;

    private Time timeTo;

    //@Enumerated
    //private ReservationStatus status;

    //private int durationInHours;

}

