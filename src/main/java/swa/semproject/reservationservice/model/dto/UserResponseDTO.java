package swa.semproject.reservationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {

    private Integer id;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private Integer tel;

    public UserResponseDTO(Integer id) {
        this.id = id;
    }
}

