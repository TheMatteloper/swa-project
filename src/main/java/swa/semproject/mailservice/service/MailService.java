package swa.semproject.mailservice.service;

import swa.semproject.mailservice.controller.MailController;
import swa.semproject.mailservice.dto.RegistrationMailRequestDTO;
import swa.semproject.mailservice.dto.ReservationMailRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    private final Logger logger = LoggerFactory.getLogger(MailController.class);

    @Value("MAIL_FROM")
    private String from;

    @Value("REGISTRATION_MAIL_SUBJECT")
    private String registrationMailSubject;

    @Value("RESERVATION_MAIL_SUBJECT")
    private String reservationMailSubject;

    @Value("RESERVATION_MAIL_BODY")
    private String reservationMailBody;

    @Value("REGISTRATION_MAIL_BODY")
    private String registrationMailBody;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationMail(RegistrationMailRequestDTO registrationMailRequestDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(registrationMailRequestDTO.getTo());
        message.setSubject(registrationMailSubject);
        message.setText(registrationMailBody);
        mailSender.send(message);

        logger.info("Mail sent to " + registrationMailRequestDTO.getTo()
        + " from " + from + " with subject "
        + registrationMailSubject + " and with body " + registrationMailBody);
    }

    public void sendReservationMail(ReservationMailRequestDTO reservationMailRequestDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(reservationMailRequestDTO.getSendTo());
        message.setSubject(reservationMailSubject);
        String body = createReservationMailBody(reservationMailRequestDTO);
        message.setText(body);
        mailSender.send(message);

        logger.info("Mail sent to " + reservationMailRequestDTO.getSendTo()
                + " from " + from + " with subject "
                + reservationMailSubject + " and with body " + body);
    }

    private String createReservationMailBody(ReservationMailRequestDTO reservationMailRequestDTO){
        return reservationMailBody + " reservation from " + reservationMailRequestDTO.getDateFrom()
                + " to " + reservationMailRequestDTO.getDateTo();
    }
}
