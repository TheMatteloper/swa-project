package cvut.swa.integrationtest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import cvut.swa.integrationtest.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationViewDTO {

    private Integer id;

    private Integer roomId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime timeFrom;

    private ReservationStatus status;

    public ReservationViewDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationViewDTO that = (ReservationViewDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(roomId, that.roomId) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, timeFrom, status);
    }

}
