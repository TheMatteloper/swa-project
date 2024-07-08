package swa.semproject.userservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swa.semproject.userservice.dto.request.RegistrationMailRequestDTO;

@FeignClient("mail-service")
public interface MailServiceClient {

    @RequestMapping(value = "/mail/registration", method = RequestMethod.POST)
    void sendRegistrationMail(@RequestBody RegistrationMailRequestDTO registrationMailRequestDTO);
}
