package ru.ivanov.securityserver.utils;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.dto.UserInfoDTO;
import ru.ivanov.securityserver.models.UserInfo;

import java.util.Collections;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Component
public class UserConverter {
    public List<UserDTO> convertToUserDTOList(List<UserDetails> allUserDetail, List<UserInfo> allUserInfo) {
        return Collections.emptyList();
    }

    public UserDTO convertToUserDTO(UserInfo userInfo, UserDetails userDetails) {
        return null;
    }

    public UserInfo convertToUserInfo(UserInfoDTO newUser) {

        return null;
    }
}
