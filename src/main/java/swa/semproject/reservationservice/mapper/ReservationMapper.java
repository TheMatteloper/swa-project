package swa.semproject.reservationservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import swa.semproject.reservationservice.model.Reservation;
import swa.semproject.reservationservice.model.dto.ReservationRequestDTO;
import swa.semproject.reservationservice.model.dto.ReservationResponseDTO;
import swa.semproject.reservationservice.model.dto.ReservationViewDTO;

@Component
public class ReservationMapper {

    private final ModelMapper modelMapper;

    public ReservationMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public ReservationResponseDTO convertToResponseDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationResponseDTO.class);
    }

    public Reservation convertToRequestEntity(ReservationRequestDTO reservationRequestDTO) {
        return modelMapper.map(reservationRequestDTO, Reservation.class);
    }

    public ReservationViewDTO convertToViewDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationViewDTO.class);
    }
}


