package com.linkedin.User_Service.Service;

import com.linkedin.User_Service.DTO.LoginRequestDto;
import com.linkedin.User_Service.DTO.SignupRequestDto;
import com.linkedin.User_Service.DTO.UserDto;

public interface AuthService {
    UserDto signUp(SignupRequestDto signupRequestDto);

    String login(LoginRequestDto loginRequestDto);
}
