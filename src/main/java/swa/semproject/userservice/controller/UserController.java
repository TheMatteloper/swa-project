package swa.semproject.userservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import swa.semproject.userservice.dto.UserResponseDTO;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content())
    })
    public UserResponseDTO getUser(@PathVariable String id) {
        try {
            return new UserResponseDTO(1, "user", "John", "Doe", "mail@g.com", 1234456);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
