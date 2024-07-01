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
import org.springframework.transaction.annotation.Transactional;
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
class ReservationServiceTest {

    @PersistenceContext
    private EntityManager em;

    @InjectMocks
    @Autowired
    private ReservationService sut;

    @Mock
    private UserServiceClient userServiceClient;

    @BeforeEach
    void initTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllOwnedReservations() {
        Integer userId = 1;
        List<Reservation> reservations = Generator.generateListOfReservationsForUser(userId);
        Set<Integer> reservationIds = new HashSet<>();
        for (Reservation reservation : reservations) {
            em.persist(reservation);
            reservationIds.add(reservation.getId());
        }

        List<ReservationViewDTO> result = sut.getAllOwnedReservations(userId);

        Set<Integer> resultIds = new HashSet<>();
        for (ReservationViewDTO view : result) {
            resultIds.add(view.getId());
        }
        Assertions.assertEquals(reservationIds, resultIds);

        for (Reservation reservation : reservations) {
            em.remove(reservation);
        }
    }

    @Test
    void getReservationById() {
        final Reservation reservation = Generator.generateReservationForUser(1);
        em.persist(reservation);

        final Reservation result = sut.getReservationById(reservation.getId());

        Assertions.assertEquals(reservation, result);
        em.remove(result);
    }

    @Test
    void createReservation() throws Exception {
        Integer userId = 1;
        when(userServiceClient.getUser(userId)).thenReturn(new UserResponseDTO(userId));

        final Reservation reservation = Generator.generateReservationForUser(userId);

        Integer resId = sut.createReservation(new ReservationRequestDTO(reservation));

        final Reservation result = sut.getReservationById(resId);
        reservation.setId(resId);
        Assertions.assertEquals(reservation, result);
        em.remove(result);
    }

    @Test
    void cancelReservation() {
        final Reservation reservation = Generator.generateUnpaidReservationForUser(1);
        em.persist(reservation);

        sut.cancelReservation(reservation.getId());

        final Reservation result = sut.getReservationById(reservation.getId());
        Assertions.assertEquals(ReservationStatus.CANCELLED, result.getStatus());
        em.remove(result);
    }
}