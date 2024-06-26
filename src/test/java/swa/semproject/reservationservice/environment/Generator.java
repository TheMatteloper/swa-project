package swa.semproject.reservationservice.environment;

import org.joda.time.DateTime;
import swa.semproject.reservationservice.enums.ReservationStatus;
import swa.semproject.reservationservice.model.Reservation;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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

    public static Reservation generateReservationForUser(Integer userId) {
        final Reservation reservation = new Reservation();
        LocalTime time = randomTime();

        reservation.setUserId(userId);
        reservation.setRoomId(randomInt());
        reservation.setDate(randomFutureDate());
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

    public static Set<Reservation> generateListOfReservationsForUser(Integer userId) {
        Set<Reservation> reservations = new HashSet<>();

        for (int i=0; i<RAND.nextInt(1,10); i++) {
            reservations.add(generateReservationForUser(userId));
        }

        return reservations;
    }

}
