package cvut.swa.integrationtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cvut.swa.integrationtest.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequestDTO {

    private Integer userId;

    private Integer roomId;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    private ReservationStatus status;

}
