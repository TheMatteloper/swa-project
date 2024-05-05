package cvut.swa.integrationtest;

import cvut.swa.integrationtest.client.UserServiceClient;
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
	public void test() {
		Assertions.assertDoesNotThrow(() -> userServiceClient.getUser("1"));
	}


}
