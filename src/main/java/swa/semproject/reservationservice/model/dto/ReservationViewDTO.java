package swa.semproject.reservationservice.model.dto;

import lombok.Data;
import swa.semproject.reservationservice.enums.ReservationStatus;

import javax.persistence.Enumerated;
import java.sql.Date;

@Data
public class ReservationViewDTO {

    private Integer id;

    private Integer roomId;

    private Date date;

    @Enumerated
    private ReservationStatus status;

}
