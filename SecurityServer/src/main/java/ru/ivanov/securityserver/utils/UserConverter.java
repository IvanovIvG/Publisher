package ru.ivanov.securityserver.utils;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.models.UserEntity;

import java.util.Collections;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Component
public class UserConverter {
    public List<UserDTO> convertToUserDTOList(List<UserDetails> allUserDetail, List<UserEntity> allUserEntity) {
        return Collections.emptyList();
    }

    public UserDTO convertToUserDTO(UserEntity userEntity, UserDetails userDetails) {
        return null;
    }

    public UserEntity convertToUserInfo(UserDTO newUser) {

        return null;
    }
}
