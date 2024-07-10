package swa.semproject.reservationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.reservationservice.client.MailServiceClient;
import swa.semproject.reservationservice.client.UserServiceClient;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.*;
import swa.semproject.reservationservice.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationService {

    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private ReservationRepository repo;

    private UserServiceClient userServiceClient;

    private MailServiceClient mailServiceClient;


    public ReservationService(ReservationRepository repo, UserServiceClient userServiceClient,
                              MailServiceClient mailServiceClient) {
        this.repo = repo;
        this.userServiceClient = userServiceClient;
        this.mailServiceClient = mailServiceClient;
    }

    public UserResponseDTO checkIfUserExists(Integer userId) throws Exception {
        UserResponseDTO user = userServiceClient.getUser(userId);

        if (!user.getId().equals(userId)) {
            logger.warn("Request failed - user not found");
            throw new Exception("Invalid user data");
        }
        return user;
    }

    public List<ReservationViewDTO> getAllOwnedReservations(Integer userId) throws Exception {

        checkIfUserExists(userId);

        return repo.findAllByUserId(userId).stream()
                .map(ReservationViewDTO::new)
                .collect(Collectors.toList());
    }


    public Reservation getReservationById(Integer reservationId) {
        Optional<Reservation> reservation = repo.getReservationById(reservationId);

        if (reservation.isEmpty()) throw new IllegalArgumentException(String.format("Reservation id %s does not exist",
                reservationId));

        return reservation.get();
    }

    public ReservationResponseDTO getReservationDTOById(Integer reservationId, Integer userId) throws Exception {

        checkIfUserExists(userId);

        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(getReservationById(reservationId));

        if (!userId.equals(reservationResponseDTO.getUserId())) {
            logger.warn(String.format("Reservation could not be returned - userId %s does not match %s", userId,
                    reservationResponseDTO.getUserId()));
            throw new Exception("Invalid request data");
        }

        return reservationResponseDTO;
    }

    public Integer createReservation(ReservationRequestDTO reservationRequestDTO) throws Exception {

        if (reservationRequestDTO.getUserId() == null ||
                reservationRequestDTO.getRoomId() == null ||
                reservationRequestDTO.getTimeFrom() == null ||
                reservationRequestDTO.getTimeTo() == null) {
            logger.warn("Reservation could not be created - invalid data");
            throw new Exception("Invalid reservation data");
        }

        // check user existence
        UserResponseDTO user = checkIfUserExists(reservationRequestDTO.getUserId());

        Reservation reservation = new Reservation(reservationRequestDTO);

        repo.save(reservation);
        logger.info(String.format("Reservation id %s created", reservation.getId()));

        //sendReservationEmail(user.getEmail());

        return reservation.getId();
    }

    public void cancelReservation(Integer reservationId) {
        Reservation reservation = getReservationById(reservationId);

        reservation.setStatus(ReservationStatus.CANCELLED);

        repo.save(reservation);
        logger.info(String.format("Reservation id %s cancelled", reservationId));
    }

    private void sendReservationEmail(String to){
        mailServiceClient.sendReservationMail(new ReservationMailRequestDTO(to));
    }
}
