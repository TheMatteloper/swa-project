package swa.semproject.reservationservice.repository;

import swa.semproject.reservationservice.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    List<Reservation> findAllByUserId(Integer userId);

    Optional<Reservation> getReservationById(Integer id);


}
