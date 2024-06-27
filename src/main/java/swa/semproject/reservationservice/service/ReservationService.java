package swa.semproject.reservationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;
import swa.semproject.reservationservice.model.dto.ReservationResponseDTO;
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

    public Set<ReservationViewDTO> getAllOwnedReservations(Integer userId) {
        return repo.findAllByUserId(userId);
    }


    public Reservation getReservationById(Integer reservationId) {
        Optional<Reservation> reservation = repo.getReservationById(reservationId);

        if (reservation.isEmpty()) throw new IllegalArgumentException(String.format("Reservation id %s does not exist",
                reservationId));

        return reservation.get();
    }

    public ReservationResponseDTO getReservationDTOById(Integer reservationId) {
        return new ReservationResponseDTO(getReservationById(reservationId));
    }

    public Integer createReservation(ReservationRequestDTO reservationRequestDTO) throws Exception {

        //TODO check available rooms?

        if (reservationRequestDTO.getUserId() == null ||
                reservationRequestDTO.getRoomId() == null ||
                reservationRequestDTO.getTimeFrom() == null ||
                reservationRequestDTO.getTimeTo() == null ||
                reservationRequestDTO.getDate() == null ||
                reservationRequestDTO.getDate().isBefore(LocalDate.now())) {
            logger.warn("Reservation could not be created - invalid data");
            throw new Exception("Invalid reservation data");
        }

        // TODO check if user exists

        Reservation reservation = new Reservation(reservationRequestDTO);

        repo.save(reservation);
        logger.info(String.format("Reservation id %s created", reservation.getId()));

        // TODO call mail service

        return reservation.getId();
    }

    public boolean cancelReservation(Integer reservationId) {
        Reservation reservation = getReservationById(reservationId);

        reservation.setStatus(ReservationStatus.CANCELLED);

        repo.save(reservation);
        logger.info(String.format("Reservation id %s cancelled", reservationId));

        return true;
    }
}
