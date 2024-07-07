package swa.semproject.mailservice;

import dto.RegistrationMailRequestDTO;
import dto.ReservationMailRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import service.MailService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


class MailServiceUnitTests {

	@Mock
	private JavaMailSender mailSender;

	private MailService mailService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mailService = new MailService(mailSender);
	}

	@Test
	public void sendRegistrationMail_shouldSendRegistrationMail(){
		mailService.sendRegistrationMail(new RegistrationMailRequestDTO("test@test.com"));
		Mockito.verify(mailSender).send(any(SimpleMailMessage.class));
		Mockito.verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
	}

	@Test
	public void sendReservationMail_shouldSendRegistrationMail(){
		mailService.sendReservationMail(new ReservationMailRequestDTO("test@test.com", "10-10-2024", "11-11-2024"));
		Mockito.verify(mailSender).send(any(SimpleMailMessage.class));
		Mockito.verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
	}

}
