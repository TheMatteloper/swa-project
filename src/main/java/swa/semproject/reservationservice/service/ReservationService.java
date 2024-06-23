package swa.semproject.reservationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;
import swa.semproject.reservationservice.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ReservationService {

    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationRepository repo;

    public ReservationService(ReservationRepository repo) {
        this.repo = repo;
    }

    public Set<ReservationViewDTO> getAllOwnedReservations() {
        Integer userId = 1; //TODO get user ID
        return repo.findAllByUserId(userId);
    }

    public Reservation getReservationById(Integer reservationId) {
        Optional<Reservation> reservation = repo.getReservationById(reservationId);

        if (reservation.isEmpty()) throw new IllegalArgumentException(String.format("Reservation id %s does not exist",
                reservationId));

        Integer userId = 1; //TODO get user ID
        Reservation result = reservation.get();
        if (!result.getUserId().equals(userId)) throw new IllegalArgumentException(String.format("Reservation id %s " +
                        "does not belong to this user", reservationId));

        return result;
    }

    public boolean createReservation(Reservation reservation) throws Exception {

        //TODO check available rooms?

        if (reservation.getRoomId() == null ||
                reservation.getTimeFrom() == null ||
                reservation.getTimeTo() == null ||
                reservation.getDate() == null ||
                reservation.getDate().isBefore(LocalDate.now())) {
            logger.info("Reservation could not be created - invalid data");
            throw new Exception("Invalid reservation data");
        }
        reservation.setId(null);

        repo.save(reservation);
        logger.info(String.format("Reservation for room id %s created", reservation.getRoomId()));

        return true;
    }

    public boolean cancelReservation(Integer reservationId) {
        Reservation reservation = getReservationById(reservationId);

        reservation.setStatus(ReservationStatus.CANCELLED);

        repo.save(reservation);
        logger.info(String.format("Reservation id %s cancelled", reservationId));

        return true;
    }
}
