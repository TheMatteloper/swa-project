package swa.semproject.userservice.service;

import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import swa.semproject.userservice.model.User;
import swa.semproject.userservice.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id \"" + id + "\" not found!"));
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

}
