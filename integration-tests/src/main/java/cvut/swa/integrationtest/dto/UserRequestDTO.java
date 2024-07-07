package cvut.swa.integrationtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {

    private String userName;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private Integer tel;

    // FIX for fasterJackson serialization
    public  UserRequestDTO() {}
}
