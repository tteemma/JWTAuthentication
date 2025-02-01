package artem.mironenko.jwtauthentication.controller;

import artem.mironenko.jwtauthentication.dto.AuthResponseDTO;
import artem.mironenko.jwtauthentication.dto.LoginDTO;
import artem.mironenko.jwtauthentication.dto.RegisterDTO;
import artem.mironenko.jwtauthentication.jwt.JWTGenerator;
import artem.mironenko.jwtauthentication.models.Role;
import artem.mironenko.jwtauthentication.models.UserEntity;
import artem.mironenko.jwtauthentication.repository.RoleRepository;
import artem.mironenko.jwtauthentication.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("User already register", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role not found"));
        userEntity.setRole(Collections.singletonList(role));

        userRepository.save(userEntity);
        System.out.printf("Username %s registered successfully!", registerDTO.getUsername());
        return new ResponseEntity<>("User successfully registered!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.printf("Authentication successful!\n");
        String token = jwtGenerator.generateJWT(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
}
