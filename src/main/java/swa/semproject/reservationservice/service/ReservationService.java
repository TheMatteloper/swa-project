package swa.semproject.reservationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.reservationservice.client.UserServiceClient;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;
import swa.semproject.reservationservice.model.dto.ReservationResponseDTO;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;
import swa.semproject.reservationservice.model.dto.UserResponseDTO;
import swa.semproject.reservationservice.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationRepository repo;

    private UserServiceClient userServiceClient;

    public ReservationService(ReservationRepository repo, UserServiceClient userServiceClient) {
        this.repo = repo;
        this.userServiceClient = userServiceClient;
    }

    public void setUserServiceClient(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public List<ReservationViewDTO> getAllOwnedReservations(Integer userId) {
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

        if (reservationRequestDTO.getUserId() == null ||
                reservationRequestDTO.getRoomId() == null ||
                reservationRequestDTO.getTimeFrom() == null ||
                reservationRequestDTO.getTimeTo() == null ||
                reservationRequestDTO.getDate() == null ||
                reservationRequestDTO.getDate().isBefore(LocalDate.now())) {
            logger.warn("Reservation could not be created - invalid data");
            throw new Exception("Invalid reservation data");
        }

        //check whether user exists
        Integer userId = reservationRequestDTO.getUserId();
        UserResponseDTO user = userServiceClient.getUser(userId);
        if (!user.getId().equals(userId)) {
            logger.warn("Reservation could not be created - user not found");
            throw new Exception("Invalid user data");
        }

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
