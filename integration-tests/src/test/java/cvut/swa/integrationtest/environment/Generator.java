package cvut.swa.integrationtest.environment;


import cvut.swa.integrationtest.dto.ReservationRequestDTO;
import cvut.swa.integrationtest.enums.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    public static int randomInt() {
        return RAND.nextInt();
    }

    public static LocalDate randomFutureDate() {
        return LocalDate.now().plusDays(1 + RAND.nextLong(1000));
    }

    public static LocalTime randomTime() {
        return LocalTime.of(RAND.nextInt(22), RAND.nextInt(59));
    }

    public static ReservationStatus randomStatus() {
        return ReservationStatus.values()[RAND.nextInt(ReservationStatus.values().length)];
    }

    public static ReservationRequestDTO generateReservationRequestDTOForUser(Integer userId) {
        final ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO();
        LocalTime time = randomTime();

        reservationRequestDTO.setUserId(userId);
        reservationRequestDTO.setDate(randomFutureDate());
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
