package swa.semproject.reservationservice.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.environment.Generator;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ReservationServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ReservationService sut;


    @Test
    void getAllOwnedReservations() {
        Integer userId = 1;
        Set<Reservation> reservations = Generator.generateListOfReservationsForUser(userId);
        Set<Integer> reservationIds = new HashSet<>();
        for (Reservation reservation : reservations) {
            em.persist(reservation);
            reservationIds.add(reservation.getId());
        }

        Set<ReservationViewDTO> result = sut.getAllOwnedReservations(userId);

        Set<Integer> resultIds = new HashSet<>();
        for (ReservationViewDTO view : result) {
            resultIds.add(view.getId());
        }
        Assert.assertEquals(reservationIds, resultIds);

        for (Reservation reservation : reservations) {
            em.remove(reservation);
        }
    }

    @Test
    void getReservationById() {
        final Reservation reservation = Generator.generateReservationForUser(1);
        em.persist(reservation);

        final Reservation result = sut.getReservationById(reservation.getId());

        Assert.assertEquals(reservation, result);
        em.remove(result);
    }

    @Test
    void createReservation() throws Exception {
        final Reservation reservation = Generator.generateReservationForUser(1);

        sut.createReservation(reservation);

        final Reservation result = sut.getReservationById(reservation.getId());
        Assert.assertEquals(reservation, result);
        em.remove(result);
    }

    @Test
    void cancelReservation() {
        final Reservation reservation = Generator.generateUnpaidReservationForUser(1);
        em.persist(reservation);

        sut.cancelReservation(reservation.getId());

        final Reservation result = sut.getReservationById(reservation.getId());
        Assert.assertEquals(ReservationStatus.CANCELLED, result.getStatus());
        em.remove(result);
    }
}