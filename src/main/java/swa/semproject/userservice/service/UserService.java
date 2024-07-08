package swa.semproject.userservice.service;

import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swa.semproject.userservice.client.MailServiceClient;
import swa.semproject.userservice.dto.request.RegistrationMailRequestDTO;
import swa.semproject.userservice.model.User;
import swa.semproject.userservice.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final MailServiceClient mailServiceClient;

    public UserService(UserRepository userRepository, MailServiceClient mailServiceClient) {
        this.userRepository = userRepository;
        this.mailServiceClient = mailServiceClient;
    }


    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id \"" + id + "\" not found!"));
    }

    public int createUser(User user) {
        userRepository.save(user);
        sendRegistrationEmail(user.getEmail());
        return user.getId();
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public void sendRegistrationEmail(String to) {
        mailServiceClient.sendRegistrationMail(new RegistrationMailRequestDTO(to));
    }
}
