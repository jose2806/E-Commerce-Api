package com.E_Commerce.E_Commerce.Api.controller;

import com.E_Commerce.E_Commerce.Api.domain.user.*;
import com.E_Commerce.E_Commerce.Api.security.SecurityConfigurations;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityConfigurations securityConfigurations;

    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid UserRegisterData data, UriComponentsBuilder uriComponentsBuilder){
        String encryptedPassword = securityConfigurations.passwordEncoder().encode(data.password());
        User user = new User(data.name(), data.email(), encryptedPassword, data.role());
        userRepository.save(user);
        UserResponseData response = new UserResponseData(user.getId(), user.getName(), user.getEmail(), user.getRole());
        URI url = uriComponentsBuilder.path("users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserListData>> userList(){
        return ResponseEntity.ok(userRepository.findAll().stream()
                .map(UserListData::new).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

}
