package swa.semproject.reservationservice.environment;

import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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

    public static Reservation generateReservationForUser(Integer userId) {
        final Reservation reservation = new Reservation();
        LocalDateTime time = randomFutureDate();

        reservation.setUserId(userId);
        reservation.setRoomId(randomInt());
        reservation.setTimeFrom(time);
        reservation.setTimeTo(time.plusHours(1));
        reservation.setStatus(randomStatus());

        return reservation;
    }

    public static Reservation generateUnpaidReservationForUser(Integer userId) {
        final Reservation reservation = generateReservationForUser(userId);
        reservation.setStatus(ReservationStatus.UNPAID);

        return reservation;
    }

    public static ReservationRequestDTO generateReservationRequestDTOForUser(Integer userId) {
        final Reservation reservation = generateReservationForUser(userId);
        return new ReservationRequestDTO(reservation);
    }

    public static List<Reservation> generateListOfReservationsForUser(Integer userId) {
        List<Reservation> reservations = new ArrayList<>();

        for (int i=0; i<RAND.nextInt(1,10); i++) {
            reservations.add(generateReservationForUser(userId));
        }

        return reservations;
    }

}
