package swa.semproject.userservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import swa.semproject.userservice.dto.request.UserRequestDTO;
import swa.semproject.userservice.dto.response.UserResponseDTO;
import swa.semproject.userservice.model.User;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO convertToDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public User convertToEntity(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }
}
