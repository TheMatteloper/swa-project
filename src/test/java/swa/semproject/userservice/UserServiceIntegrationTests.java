package swa.semproject.userservice;

import jakarta.ws.rs.NotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swa.semproject.userservice.model.User;
import swa.semproject.userservice.service.UserService;

@SpringBootTest
class UserServiceIntegrationTests {

	@Autowired
	private UserService userService;

	public static User generateRandomUser() {
		User user = new User();
		user.setName("John");
		user.setLogin(generateRandomString()); // has to be unique
		user.setEmail("john@gmail.com");
		user.setSurname("Doe");
		user.setPassword("1234");
		user.setTelephone("993 000 123");
		return user;
	}

	public static String generateRandomString(){
		return RandomStringUtils.randomAlphanumeric(10);
	}


	@Test
	void getUserById_ShouldReturnUser() {
		User user = generateRandomUser();
		userService.createUser(user);
		Assertions.assertNotNull(userService.getUserById(user.getId()));;
	}

	@Test
	void getUserById_ShouldInvokeNotFoundExceptionForNotExistingUser() {
		Assertions.assertThrows(NotFoundException.class,() -> userService.getUserById(99999));
	}

	@Test
	void createUser_ShouldCreateUser() {
		User user = generateRandomUser();
		userService.createUser(user);
		Assertions.assertNotNull(userService.getUserById(user.getId()));
	}

	@Test
	void deleteUserById_ShouldDeleteUser() {
		User user = generateRandomUser();
		userService.createUser(user);
		userService.deleteUserById(user.getId());
		Assertions.assertThrows(NotFoundException.class,() -> userService.getUserById(user.getId()));
	}

	@Test
	void deleteUserById_ShouldNotInvokeNotFoundExceptionForNotExistingUser() {
		Assertions.assertDoesNotThrow(() -> userService.deleteUserById(1));
	}
}
