package swa.semproject.userservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import swa.semproject.userservice.dto.UserResponseDTO;
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
}
