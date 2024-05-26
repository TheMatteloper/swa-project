package swa.semproject.reservationservice.model.dto;

import swa.semproject.reservationservice.enums.ReservationStatus;

import javax.persistence.Enumerated;
import java.sql.Date;

public class ReservationViewDTO {

    private Integer roomId;

    private Date date;

    @Enumerated
    private ReservationStatus status;

}
