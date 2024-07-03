package swa.semproject.userservice.controller;

import feign.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import swa.semproject.userservice.dto.request.UserRequestDTO;
import swa.semproject.userservice.dto.response.UserResponseDTO;
import swa.semproject.userservice.mapper.UserMapper;
import swa.semproject.userservice.service.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

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
            logger.info("GET request - getUser - /user/{id} - user found");
            return userMapper.convertToDto(userService.getUserById(id));
        } catch (Exception e) {
            logger.info("GET request - getUser - /user/{id} - user not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content())
    })
    public void createUser(@RequestBody UserRequestDTO userRequestDTO) {
        logger.info("POST request - createUser");
        userService.createUser(userMapper.convertToEntity(userRequestDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted or not existing"),
            @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content())
    })
    public void deleteUser(@PathVariable Integer id) {
        logger.info("DELETE request - deleteUser - /user/{id}");
        userService.deleteUserById(id);
    }

}
