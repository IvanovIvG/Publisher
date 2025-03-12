package ru.ivanov.securityserver.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.securityserver.dto.UserInfo;
import ru.ivanov.securityserver.dto.UserDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @GetMapping(produces = "application/json")
    public List<UserDTO> showAllUsers(){
        return null;
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    public UserDTO showUser(@PathVariable UUID userId){
        return null;
    }

    @PostMapping(produces = "application/json")
    public UserDTO createUser(@RequestBody @Valid UserInfo newUser){
        return null;
    }

    @PutMapping(path = "/{userId}", produces = "application/json")
    public UserDTO updateUser(@PathVariable UUID userId,
                              @RequestBody @Valid UserInfo updatedUser){
        return null;
    }

    @PutMapping(path = "/{userId}/role")
    public String changeRole(@PathVariable UUID userId,
                      @RequestBody String newRole){
        return null;
    }

    @PutMapping(path = "/{userId}/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@PathVariable UUID userId,
                               @RequestBody String newPassword){

    }

    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable UUID userId){

    }

}
