package ru.ivanov.securityserver.services;

import com.fasterxml.uuid.Generators;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.securityserver.dto.PasswordDTO;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.models.UserEntity;
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
        List<UserEntity> allUserEntity = userInfoRepository.findAll();
        List<UserDetails> allUserDetail = readAllUserDetails(allUserEntity);
        return userConverter.convertToUserDTOList(allUserEntity, allUserDetail);
    }

    public UserDTO readById(UUID userId) {
        UserEntity userEntity = readUserEntityById(userId);
        UserDetails userDetails = readUserDetails(userEntity);
        return userConverter.convertToUserDTO(userEntity, userDetails);
    }

    public UserDTO readByUserName(String username) {
        UserEntity userEntity = readUserEntityByUsername(username);
        UserDetails userDetails = readUserDetails(userEntity);
        return userConverter.convertToUserDTO(userEntity, userDetails);
    }

    @Transactional
    public UserDTO create(UserDTO newUser, String password) {
        UserEntity newUserEntity = userConverter.convertToUserInfo(newUser);
        newUserEntity.setId(generateUniqueUserInfoID());
        newUserEntity = userInfoRepository.save(newUserEntity);

        UserDetails newUserDetails = User.withUsername(newUserEntity.getUsername())
                .password(password)
                .authorities("ROLE_USER")
                .build();
        userDetailsRepository.createUser(newUserDetails);

        return userConverter.convertToUserDTO(newUserEntity, newUserDetails);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        UserEntity userEntity = readUserEntityById(userId);

        userDetailsRepository.deleteUser(userEntity.getUsername());
        userInfoRepository.deleteById(userId);
    }

    @Transactional
    public UserDTO changeRole(UUID userId, GrantedAuthority newRole) {
        UserEntity userEntity = readUserEntityById(userId);
        UserDetails userDetails = readUserDetails(userEntity);
        UserDetails updatedUser = new User(userDetails.getUsername(), userDetails.getPassword(), List.of(newRole));

        userDetailsRepository.updateUser(updatedUser);

        return userConverter.convertToUserDTO(userEntity, updatedUser);
    }

    @Transactional
    public void changePassword(PasswordDTO passwordDTO) {
        String oldPassword = passwordDTO.getOldPassword();
        String newPassword = passwordDTO.getNewPassword();
        try {
            userDetailsRepository.changePassword(oldPassword, newPassword);
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Wrong password");
        }
    }

    private UserEntity readUserEntityById(UUID userId) {
        return userInfoRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("There is no user with such id"));
    }

    private UserEntity readUserEntityByUsername(String username) {
        return userInfoRepository.findByUsername(username).
                orElseThrow(() -> new IllegalArgumentException("There is no user with such username"));
    }

    private List<UserDetails> readAllUserDetails(List<UserEntity> allUserEntity) {
        List<UserDetails> allUserDetail = new ArrayList<>();
        for (UserEntity userEntity : allUserEntity) {
            UserDetails userDetails = readUserDetails(userEntity);
            allUserDetail.add(userDetails);
        }
        return allUserDetail;
    }

    private UserDetails readUserDetails(UserEntity userEntity) {
        try {
            return userDetailsRepository.loadUserByUsername(userEntity.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("User's username in different tables is not the same");
        }
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
