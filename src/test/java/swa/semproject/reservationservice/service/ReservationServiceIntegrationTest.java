package swa.semproject.reservationservice.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.reservationservice.client.MailServiceClient;
import swa.semproject.reservationservice.client.UserServiceClient;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.environment.Generator;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;
import swa.semproject.reservationservice.model.dto.UserResponseDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;


@SpringBootTest
@Transactional
class ReservationServiceIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    @MockBean
    private UserServiceClient userServiceClient;

    @MockBean
    private MailServiceClient mailServiceClient;

    @Autowired
    @InjectMocks
    private ReservationService sut;

    @BeforeEach
    public void initTest() {
        MockitoAnnotations.openMocks(this);

        Integer userId = 1;
        when(userServiceClient.getUser(userId)).thenReturn(new UserResponseDTO(userId));
    }


    @Test
    public void getAllOwnedReservations() throws Exception {
        // Arrange
        Integer userId = 1;
        List<Reservation> reservations = Generator.generateListOfReservationsForUser(userId);
        Set<Integer> reservationIds = new HashSet<>();
        for (Reservation reservation : reservations) {
            em.persist(reservation);
            reservationIds.add(reservation.getId());
        }

        // Act
        List<ReservationViewDTO> result = sut.getAllOwnedReservations(userId);

        Set<Integer> resultIds = new HashSet<>();
        for (ReservationViewDTO view : result) {
            resultIds.add(view.getId());
        }
        // Assert
        Assertions.assertEquals(reservationIds, resultIds);

        for (Reservation reservation : reservations) {
            em.remove(reservation);
        }
    }

    @Test
    public void getReservationById() {
        // Arrange
        final Reservation reservation = Generator.generateReservationForUser(1);
        em.persist(reservation);

        // Act
        final Reservation result = sut.getReservationById(reservation.getId());

        // Assert
        Assertions.assertEquals(reservation, result);
        em.remove(result);
    }

    @Test
    public void createReservation() throws Exception {
        // Arrange
        Integer userId = 1;
        final Reservation reservation = Generator.generateReservationForUser(userId);

        // Act
        Integer resId = sut.createReservation(new ReservationRequestDTO(reservation));

        final Reservation result = sut.getReservationById(resId);
        reservation.setId(resId);
        // Assert
        Assertions.assertEquals(reservation, result);
        em.remove(result);
    }

    @Test
    public void cancelReservation() {
        // Arrange
        final Reservation reservation = Generator.generateUnpaidReservationForUser(1);
        em.persist(reservation);

        // Act
        sut.cancelReservation(reservation.getId());

        final Reservation result = sut.getReservationById(reservation.getId());
        // Assert
        Assertions.assertEquals(ReservationStatus.CANCELLED, result.getStatus());
        em.remove(result);
    }
}