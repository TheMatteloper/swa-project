package swa.semproject.userservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import swa.semproject.userservice.client.MailServiceClient;
import swa.semproject.userservice.dto.request.RegistrationMailRequestDTO;
import swa.semproject.userservice.model.User;
import swa.semproject.userservice.repository.UserRepository;
import swa.semproject.userservice.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceUnitTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MailServiceClient mailServiceClient;

    private UserService userService;

    // Mocks UserRepository when persisting entity
    public static User userSetId(User user){
        user.setId(1);
        return user;
    }

    @BeforeEach
    void initUseCase() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, mailServiceClient);
        //thenReturn() is computed at the time the mock behavior is set up
        //thenAnswer, then are dynamic computed
        //when(userRepository.save(any(User.class))).thenReturn(userSetId(user));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User arg = invocation.getArgument(0);
            return userSetId(arg);
        });
    }


    @Test
    void createUser_shouldCreateUser() {
        User user = UserServiceIntegrationTests.generateRandomUser();
        userService.createUser(user);
        assertThat(user.getId() == 1).isEqualTo(true);
    }

    @Test
    void createUser_shouldSendRegistrationEmail() {
        User user = UserServiceIntegrationTests.generateRandomUser();
        userService.createUser(user);

        Mockito.verify(mailServiceClient).sendRegistrationMail(any(RegistrationMailRequestDTO.class));
        Mockito.verify(mailServiceClient, times(1)).sendRegistrationMail(any(RegistrationMailRequestDTO.class));
    }

    @Test
    void getUserById_shouldReturnUser() {
        User user = UserServiceIntegrationTests.generateRandomUser();
        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(user));
        userRepository.save(user);
        assertThat(user.equals(userService.getUserById(user.getId()))).isEqualTo(true);

    }

    @Test
    void deleteUserById_shouldCallDeleteById() {
        User user = UserServiceIntegrationTests.generateRandomUser();
        userService.createUser(user);
        userService.deleteUserById(user.getId());
        Mockito.verify(userRepository).deleteById(anyInt());
        Mockito.verify(userRepository, times(1)).deleteById(anyInt());
    }

}
