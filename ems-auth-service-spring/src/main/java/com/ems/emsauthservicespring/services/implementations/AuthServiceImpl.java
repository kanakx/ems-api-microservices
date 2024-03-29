package com.ems.emsauthservicespring.services.implementations;

import com.ems.emsauthservicespring.config.security.JwtService;
import com.ems.emsauthservicespring.entities.dtos.*;
import com.ems.emsauthservicespring.entities.enums.UserRole;
import com.ems.emsauthservicespring.entities.mappers.UserMapper;
import com.ems.emsauthservicespring.entities.models.User;
import com.ems.emsauthservicespring.exceptions.CustomApiException;
import com.ems.emsauthservicespring.exceptions.ExceptionMessage;
import com.ems.emsauthservicespring.repositories.UserRepository;
import com.ems.emsauthservicespring.services.interfaces.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto register(RegisterUserDto registerUserDto) {
        logger.info("Processing request to register user with email: {}", registerUserDto.getEmail());
        userRepository.findByEmail(registerUserDto.getEmail()).ifPresent(user -> {
            logger.warn("Registration attempt with already existing email: {}", registerUserDto.getEmail());
            throw CustomApiException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(ExceptionMessage.entityAlreadyExists("User"))
                    .build();
        });

        String encodedPassword = passwordEncoder.encode(registerUserDto.getPassword());
        User user = User.builder()
                .email(registerUserDto.getEmail())
                .password(encodedPassword)
                .userRole(UserRole.USER)
                .build();

        User saved = userRepository.save(user);
        logger.info("Request to register user with email: {} processed successfully", saved.getEmail());

        return userMapper.mapToDto(saved);
    }

    @Override
    public TokenDto login(LoginUserDto loginUserDto) {
        logger.info("Processing request to login user with email: {}", loginUserDto.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword())
        );

        User user = userRepository.findByEmail(loginUserDto.getEmail())
                .orElseThrow(() -> {
                    logger.warn("Login attempt for non-existent email: {}", loginUserDto.getEmail());
                    return CustomApiException.builder()
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .message(ExceptionMessage.entityNotFound("User"))
                            .build();
                });

        String token = jwtService.generateToken(user);
        logger.info("Request to login user with email: {} processed successfully", loginUserDto.getEmail());

        return TokenDto.builder()
                .token(token)
                .build();
    }

    @Override
    public TokenValidationResponseDto validateToken(TokenDto tokenDto) {
        logger.info("Processing request to validate token");
        try {
            jwtService.validateToken(tokenDto.getToken());
            logger.info("Request to validate token processed successfully");
            return TokenValidationResponseDto.builder()
                    .isValid(true)
                    .token(tokenDto.getToken())
                    .build();
        } catch (ExpiredJwtException e) {
            logger.error("Token expired: {}", e.getMessage(), e);
            throw CustomApiException.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .message("Token expired")
                    .build();
        } catch (MalformedJwtException e) {
            logger.error("Invalid token format: {}", e.getMessage(), e);
            throw CustomApiException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Invalid token format")
                    .build();
        } catch (JwtException e) {
            logger.error("JWT error: {}", e.getMessage(), e);
            throw CustomApiException.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .message("Invalid token")
                    .build();
        }
    }

}
