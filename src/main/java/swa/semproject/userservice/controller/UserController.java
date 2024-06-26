package swa.semproject.userservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import swa.semproject.userservice.dto.UserResponseDTO;
import swa.semproject.userservice.mapper.UserMapper;
import swa.semproject.userservice.service.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content())
    })
    public UserResponseDTO getUser(@PathVariable Integer id) {
        try {
            return userMapper.convertToDto(userService.getUserById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
