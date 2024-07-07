package cvut.swa.integrationtest.client;


import cvut.swa.integrationtest.dto.UserRequestDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IntegrationTestsApplicationTests {

	@Autowired
	private UserServiceClient userServiceClient;


	@Test
	public void createUser_getUserReturnsCreatedUser() {
		userServiceClient.createUser(new UserRequestDTO("test", "test", "test",
				"test@test.com", 123456789));
		Assertions.assertDoesNotThrow(() -> userServiceClient.getUser("1"));
	}


}
