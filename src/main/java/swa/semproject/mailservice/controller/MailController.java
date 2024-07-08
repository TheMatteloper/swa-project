package swa.semproject.mailservice.controller;

import swa.semproject.mailservice.dto.RegistrationMailRequestDTO;
import swa.semproject.mailservice.dto.ReservationMailRequestDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import swa.semproject.mailservice.service.MailService;

@RestController
@RequestMapping(value = "/mail")
public class MailController {

    private final MailService mailService;

    private final Logger logger = LoggerFactory.getLogger(MailController.class);

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration mail successfully send"),
            @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content())
    })
    public void sendRegistrationMail(@RequestBody RegistrationMailRequestDTO registrationMailRequestDTO) {
        logger.info("POST request - send registration mail");
        mailService.sendRegistrationMail(registrationMailRequestDTO);
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation mail successfully send"),
            @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content())
    })
    public void sendReservationMail(@RequestBody ReservationMailRequestDTO reservationMailRequestDTO){
        logger.info("POST request - send reservation mail");
        mailService.sendReservationMail(reservationMailRequestDTO);
    }
}
