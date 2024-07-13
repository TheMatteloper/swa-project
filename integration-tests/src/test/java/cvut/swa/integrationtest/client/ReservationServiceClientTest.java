package cvut.swa.integrationtest.client;

import cvut.swa.integrationtest.dto.UserRequestDTO;
import cvut.swa.integrationtest.enums.ReservationStatus;
import cvut.swa.integrationtest.dto.ReservationRequestDTO;
import cvut.swa.integrationtest.dto.ReservationResponseDTO;
import cvut.swa.integrationtest.dto.ReservationViewDTO;
import cvut.swa.integrationtest.environment.Generator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationServiceClientTest {

    @Autowired
    private ReservationServiceClient reservationServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    private static Integer userId;
    private static final Integer numberOfInstances = 5;

    @Test
    @Order(1)
    public void initUser() {
        userId = userServiceClient.createUser(new UserRequestDTO("username" + Generator.randomInt(),
                "test1", "test1", "password1", "test@test.com", 123456789));
    }

    @Test
    @Order(2)
    void createNewReservations() {
        // Arrange
        List<ReservationRequestDTO> reservations = Generator.generateListOfReservationRequestDTOForUser(userId,
                numberOfInstances);
        // Act and Assert
        for (ReservationRequestDTO reservation : reservations) {
            Assertions.assertDoesNotThrow(() -> reservationServiceClient.createReservation(reservation));
        }
    }

    @Test
    @Order(3)
    void getOwnedReservations() {
        // Act
        List<ReservationViewDTO> result = reservationServiceClient.getOwnedReservations(userId).getBody();

        Set<Integer> resultIds = new HashSet<>();
        for (ReservationViewDTO view : result) {
            resultIds.add(view.getId());
        }
        // Assert
        Assertions.assertEquals(numberOfInstances, resultIds.size());
    }

    @Test
    @Order(4)
    void getReservationById() {
        // Arrange
        List<ReservationViewDTO> result = reservationServiceClient.getOwnedReservations(userId).getBody();
        Integer resId = result.get(0).getId();

        // Act
        ReservationResponseDTO reservationResponseDTO = reservationServiceClient.getReservationById(resId, userId)
                .getBody();

        // Assert
        Assertions.assertEquals(resId, reservationResponseDTO.getId());
        Assertions.assertEquals(userId, reservationResponseDTO.getUserId());
    }

    @Test
    @Order(5)
    void cancelReservation() {
        // Arrange
        List<ReservationViewDTO> result = reservationServiceClient.getOwnedReservations(userId).getBody();
        Integer resId = result.get(0).getId();

        // Act
        Assertions.assertDoesNotThrow(() -> reservationServiceClient.cancelReservation(resId));

        // Assert
        ReservationResponseDTO reservationResponseDTO = reservationServiceClient.getReservationById(resId, userId)
                .getBody();
        Assertions.assertEquals(ReservationStatus.CANCELLED, reservationResponseDTO.getStatus());
        Assertions.assertEquals(resId, reservationResponseDTO.getId());
    }

}