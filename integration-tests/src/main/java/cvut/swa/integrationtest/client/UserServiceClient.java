package cvut.swa.integrationtest.client;

import cvut.swa.integrationtest.dto.UserRequestDTO;
import cvut.swa.integrationtest.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-service", url = "http://docker:8234")
public interface UserServiceClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    UserResponseDTO getUser(@PathVariable String id);

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    void createUser(@RequestBody UserRequestDTO userRequestDTO);
}
