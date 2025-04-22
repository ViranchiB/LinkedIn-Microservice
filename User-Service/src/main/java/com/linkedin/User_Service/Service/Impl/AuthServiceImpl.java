package com.linkedin.User_Service.Service.Impl;

import com.linkedin.User_Service.DTO.LoginRequestDto;
import com.linkedin.User_Service.DTO.SignupRequestDto;
import com.linkedin.User_Service.DTO.UserDto;
import com.linkedin.User_Service.Entity.User;
import com.linkedin.User_Service.Exceptions.BadRequestException;
import com.linkedin.User_Service.Exceptions.ResourceNotFoundException;
import com.linkedin.User_Service.Repository.UserRepository;
import com.linkedin.User_Service.Service.AuthService;
import com.linkedin.User_Service.Util.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTServiceImpl jwtServiceImpl;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, JWTServiceImpl jwtServiceImpl) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtServiceImpl = jwtServiceImpl;
    }

    @Override
    public UserDto signUp(SignupRequestDto signupRequestDto) {
        log.debug("Trying to register a new user....");

        // Check if email is already exists or not
        boolean exists = this.userRepository.findByEmail(signupRequestDto.getEmail()).isPresent();
        if(exists){
            throw new BadRequestException("Email already exists.");
        }

        // Convert the DTO to Entity
        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

        // Save in DB
        User saveUser = this.userRepository.save(user);

        log.debug("User registered successfully....");
        return this.modelMapper.map(saveUser, UserDto.class);
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {

        log.debug("User trying to login....");
        // Check if user exists in DB or not
        User user = this.userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User not found with email: " + loginRequestDto.getEmail()));

        // Check the user given password matches the DB password
        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(), user.getPassword());

        // If password not match throw exception
        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect Password");
        }

        log.debug("User logged in successfully....");
        // If password match return the JWT Token
        return jwtServiceImpl.generateAccessToken(user);
    }
}
