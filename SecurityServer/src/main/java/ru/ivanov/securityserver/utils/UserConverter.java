package ru.ivanov.securityserver.utils;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.models.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Component
public class UserConverter {
    private final ModelMapper modelMapper;
    private final TypeMap<UserDetails, UserDTO> detailsToDTOMap;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.detailsToDTOMap = modelMapper.createTypeMap(UserDetails.class, UserDTO.class);
        Converter<Collection<GrantedAuthority>, GrantedAuthority> collectionToGrantedAuthority =
                c -> c.getSource().stream().toList().get(0);
        detailsToDTOMap.addMappings(mapper ->
                mapper.using(collectionToGrantedAuthority).map(UserDetails::getAuthorities, UserDTO::setRole));

    }

    public List<UserDTO> convertToUserDTOList(List<UserEntity> allUserEntity, List<UserDetails> allUserDetail) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity userEntity:allUserEntity){
            String username = userEntity.getUsername();
            UserDetails userDetail = allUserDetail.stream().filter(user -> user.getUsername().equals(username))
                    .toList().get(0);
            UserDTO userDTO = convertToUserDTO(userEntity, userDetail);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public UserDTO convertToUserDTO(UserEntity userEntity, UserDetails userDetails) {
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        detailsToDTOMap.map(userDetails, userDTO);
        return userDTO;
    }

    public UserEntity convertToUserInfo(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
