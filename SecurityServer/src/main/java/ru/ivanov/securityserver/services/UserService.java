package ru.ivanov.securityserver.services;

import com.fasterxml.uuid.Generators;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.securityserver.dto.Role;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.dto.UserInfoDTO;
import ru.ivanov.securityserver.models.UserInfo;
import ru.ivanov.securityserver.repositories.UserInfoRepository;
import ru.ivanov.securityserver.utils.UserConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Service
@Transactional(readOnly = true)
public class UserService {
    private final JdbcUserDetailsManager userDetailsRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserConverter userConverter;

    public UserService(UserDetailsService userDetailsRepository, UserInfoRepository userInfoRepository, UserConverter userConverter) {
        this.userDetailsRepository = (JdbcUserDetailsManager) userDetailsRepository;
        this.userInfoRepository = userInfoRepository;
        this.userConverter = userConverter;
    }

    public List<UserDTO> readAll() {
        List<UserInfo> allUserInfo = userInfoRepository.findAll();
        List<UserDetails> allUserDetail = readAllUserDetails(allUserInfo);
        return userConverter.convertToUserDTOList(allUserDetail, allUserInfo);
    }

    public UserDTO readById(UUID userId) {
        UserInfo userInfo = readUserInfo(userId);
        UserDetails userDetails = readUserDetails(userInfo);
        return userConverter.convertToUserDTO(userInfo, userDetails);
    }

    @Transactional
    public UserDTO create(UserInfoDTO newUser, String password) {
        UserInfo newUserInfo = userConverter.convertToUserInfo(newUser);
        newUserInfo.setId(generateUniqueUserInfoID());
        newUserInfo = userInfoRepository.save(newUserInfo);

        UserDetails newUserDetails = User.withUsername(newUserInfo.getUsername())
                .password(password)
                .authorities("ROLE_READ")
                .build();
        userDetailsRepository.createUser(newUserDetails);

        return userConverter.convertToUserDTO(newUserInfo, newUserDetails);
    }

    @Transactional
    public UserDTO update(UserInfoDTO updatedUser) {
        UserInfo updatedUserInfo = userConverter.convertToUserInfo(updatedUser);
        if (userInfoExists(updatedUserInfo)) {
            updatedUserInfo = userInfoRepository.save(updatedUserInfo);
        } else {
            throw  new RuntimeException("There is no user with such id");
        }

        UserDetails userDetails = readUserDetails(updatedUserInfo);
        return userConverter.convertToUserDTO(updatedUserInfo, userDetails);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        UserInfo userInfo = readUserInfo(userId);

        userDetailsRepository.deleteUser(userInfo.getUsername());
        userInfoRepository.deleteById(userId);
    }

    @Transactional
    public UserDTO changeRole(UUID userId, Role newRole) {
        UserInfo userInfo = readUserInfo(userId);
        UserDetails userDetails = readUserDetails(userInfo);
        UserDetails updatedUser = User.withUsername(userDetails.getUsername())
                .password(userDetails.getPassword())
                .authorities(newRole::toString)
                .build();

        userDetailsRepository.updateUser(updatedUser);

        return userConverter.convertToUserDTO(userInfo, userDetails);
    }

    @Transactional
    public void changePassword(UUID userId, String newPassword) {
        UserInfo userInfo = readUserInfo(userId);
        UserDetails userDetails = readUserDetails(userInfo);
        UserDetails updatedUser = User.withUsername(userDetails.getUsername())
                .password(newPassword)
                .authorities(userDetails.getPassword())
                .build();

        userDetailsRepository.updateUser(updatedUser);
    }

    private UserInfo readUserInfo(UUID userId) {
        return userInfoRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("There is no article with such id"));
    }

    private List<UserDetails> readAllUserDetails(List<UserInfo> allUserInfo) {
        List<UserDetails> allUserDetail = new ArrayList<>();
        for (UserInfo userInfo : allUserInfo) {
            UserDetails userDetails = readUserDetails(userInfo);
            allUserDetail.add(userDetails);
        }
        return allUserDetail;
    }

    private UserDetails readUserDetails(UserInfo userInfo) {
        try {
            return userDetailsRepository.loadUserByUsername(userInfo.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("User's username in different tables is not the same");
        }
    }

    private boolean userInfoExists(UserInfo UserInfo) {
        UUID id = UserInfo.getId();
        return userInfoRepository.existsById(id);
    }

    private UUID generateUniqueUserInfoID() {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        while (userInfoIdIsNotUnique(id)) {
            id = Generators.timeBasedEpochGenerator().generate();
        }
        return id;
    }

    private boolean userInfoIdIsNotUnique(UUID id) {
        return userInfoRepository.findById(id).isPresent();
    }
}
