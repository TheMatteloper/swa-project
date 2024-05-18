package swa.semproject.reservationservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.reservationservice.model.Reservation;
import events.ReservationCreated;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    public List<Reservation> getAllOwnedReservations() {
        return null;
    }

    public Reservation getReservationById(Integer reservationId) {
        return null;
    }

    public boolean createReservation(Reservation reservation) {

        ReservationCreated event = new ReservationCreated(  // TODO move to service
                reservation.getUserId(),
                reservation.getId(),
                reservation.getTotalPrice()
        );

        return false;
    }

    public boolean cancelReservation(Integer reservationId) {
        return false;
    }
}
