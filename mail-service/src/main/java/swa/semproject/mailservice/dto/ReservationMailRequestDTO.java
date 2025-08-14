package swa.semproject.mailservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationMailRequestDTO {

    private String sendTo;

    private String dateFrom;

    private String dateTo;
}
