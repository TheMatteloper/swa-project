package swa.semproject.reservationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swa.semproject.reservationservice.model.dto.UserResponseDTO;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    UserResponseDTO getUser(@PathVariable Integer id);

}

