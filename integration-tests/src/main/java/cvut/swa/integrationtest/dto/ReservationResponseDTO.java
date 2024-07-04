package cvut.swa.integrationtest.dto;

import cvut.swa.integrationtest.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationResponseDTO {

    private Integer id;

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    private ReservationStatus status;

}
