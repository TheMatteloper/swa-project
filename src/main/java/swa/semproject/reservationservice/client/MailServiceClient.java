package swa.semproject.reservationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swa.semproject.reservationservice.model.dto.ReservationMailRequestDTO;

@FeignClient(name = "mail-service")
public interface MailServiceClient {

    @RequestMapping(value = "/mail/reservation", method = RequestMethod.POST)
    void sendReservationMail(@RequestBody ReservationMailRequestDTO reservationMailRequestDTO);
}
