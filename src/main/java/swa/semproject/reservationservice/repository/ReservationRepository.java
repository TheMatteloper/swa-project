package swa.semproject.reservationservice.repository;

import swa.semproject.reservationservice.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    Set<ReservationViewDTO> findAllByUserId(Integer userId);

    Optional<Reservation> getReservationById(Integer Id);


}
