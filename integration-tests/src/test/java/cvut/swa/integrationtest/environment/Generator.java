package cvut.swa.integrationtest.environment;


import cvut.swa.integrationtest.dto.ReservationRequestDTO;
import cvut.swa.integrationtest.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    public static int randomInt() {
        return RAND.nextInt();
    }

    public static LocalDateTime randomFutureDate() {
        return LocalDateTime.now().plusDays(RAND.nextInt(20));
    }

    public static ReservationStatus randomStatus() {
        return ReservationStatus.values()[RAND.nextInt(ReservationStatus.values().length)];
    }

    public static ReservationRequestDTO generateReservationRequestDTOForUser(Integer userId) {
        final ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO();
        LocalDateTime time = randomFutureDate();

        reservationRequestDTO.setUserId(userId);
        reservationRequestDTO.setTimeFrom(time);
        reservationRequestDTO.setTimeTo(time.plusHours(1));
        reservationRequestDTO.setStatus(randomStatus());

        return reservationRequestDTO;
    }

    public static ReservationRequestDTO generateUnpaidReservationRequestDTOForUser(Integer userId) {
        final ReservationRequestDTO reservation = generateReservationRequestDTOForUser(userId);
        reservation.setStatus(ReservationStatus.UNPAID);

        return reservation;
    }

    public static List<ReservationRequestDTO> generateListOfReservationRequestDTOForUser(Integer userId,
                                                                                         Integer number) {
        List<ReservationRequestDTO> reservations = new ArrayList<>();

        for (int i=0; i<number; i++) {
            reservations.add(generateReservationRequestDTOForUser(userId));
        }

        return reservations;
    }

}
