package swa.semproject.reservationservice.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.reservationservice.client.MailServiceClient;
import swa.semproject.reservationservice.client.UserServiceClient;
import swa.semproject.reservationservice.environment.Generator;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;
import swa.semproject.reservationservice.model.dto.UserResponseDTO;
import swa.semproject.reservationservice.repository.ReservationRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ReservationServiceUnitTest {

    @Mock
    private ReservationRepository repo;

    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    MailServiceClient mailServiceClient;

    @InjectMocks
    private ReservationService sut;

    @BeforeEach
    public void initTest() {
        MockitoAnnotations.openMocks(this);

        when(userServiceClient.getUser(1)).thenReturn(new UserResponseDTO(1));

        //Integer userId = 1;
        //when(userServiceClient.getUser(userId)).thenReturn(new UserResponseDTO(userId));
    }

    @AfterEach
    public void resetMocks() {
        reset(repo);
        reset(userServiceClient);
    }

    @Test
    public void getAllOwnedReservations() throws Exception {
        // Arrange
        Integer userId = 1;
        List<Reservation> reservations = Generator.generateListOfReservationsForUser(userId);
        Set<Integer> reservationIds = new HashSet<>();
        for (Reservation reservation : reservations) {
            reservationIds.add(reservation.getId());
        }
        when(repo.findAllByUserId(userId)).thenReturn(convertToViewDTO(reservations));

        // Act
        List<ReservationViewDTO> result = sut.getAllOwnedReservations(userId);

        Set<Integer> resultIds = new HashSet<>();
        for (ReservationViewDTO view : result) {
            resultIds.add(view.getId());
        }

        // Assert
        verify(userServiceClient).getUser(userId);
        Assertions.assertEquals(reservationIds, resultIds);
    }

    /** helper method **/
    List<ReservationViewDTO> convertToViewDTO(List<Reservation> reservations) {
        List<ReservationViewDTO> reservationViewDTOS = new ArrayList<>();
        for (Reservation r : reservations) {
            reservationViewDTOS.add(new ReservationViewDTO(r.getId(), r.getRoomId(), r.getDate(), r.getStatus()));
        }
        return reservationViewDTOS;
    }

    @Test
    public void getReservationById() {
        // Arrange
        Integer resId = 1;
        final Reservation reservation = Generator.generateReservationForUser(resId);
        reservation.setId(resId);
        when(repo.getReservationById(resId)).thenReturn(Optional.of(reservation));

        // Act
        final Reservation result = sut.getReservationById(resId);

        // Assert
        Assertions.assertEquals(reservation, result);
    }

    @Test
    public void createReservation() {
        // Arrange
        Integer userId = 1;
        final Reservation reservation = Generator.generateReservationForUser(userId);
        when(repo.save(reservation)).thenReturn(null);

        // Act and Assert
        Assertions.assertDoesNotThrow(() -> sut.createReservation(new ReservationRequestDTO(reservation)));

        // Assert
        verify(userServiceClient).getUser(userId);
        verify(repo).save(any(Reservation.class));
    }

    @Test
    public void createReservation_nullRoomId_ThrowsError() {
        // Arrange
        Integer userId = 1;
        final Reservation reservation = Generator.generateReservationForUser(userId);
        reservation.setRoomId(null);
        when(repo.save(reservation)).thenReturn(null);

        // Act and Assert
        Assertions.assertThrows(Exception.class, () -> sut.createReservation(new ReservationRequestDTO(reservation)));
    }

    @Test
    public void cancelReservation() {
        // Arrange
        Integer resId = 1;
        final Reservation reservation = Generator.generateUnpaidReservationForUser(1);
        reservation.setId(resId);
        when(repo.getReservationById(resId)).thenReturn(Optional.of(reservation));

        // Act
        sut.cancelReservation(reservation.getId());

        // Assert
        verify(repo).save(any(Reservation.class));
    }
}