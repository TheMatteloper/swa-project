package cvut.swa.integrationtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cvut.swa.integrationtest.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequestDTO {

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    private ReservationStatus status;

}
