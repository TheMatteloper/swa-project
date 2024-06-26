package swa.semproject.userservice;

import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swa.semproject.userservice.model.User;
import swa.semproject.userservice.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;


	@Test
	void getNonExistingUserById() {
		createUser();
		Assertions.assertThrows(NotFoundException.class,() -> userService.getUserById(99999));
	}

	void createUser() {
		User user = new User();
		user.setName("John");
		user.setLogin("Xjohn");
		user.setEmail("@sad");
		user.setSurname("Doe");
		user.setPassword("123");
		user.setTelephone("993 000 123");
		userService.createUser(user);
		Assertions.assertNotNull(userService.getUserById(user.getId()));
	}

}
