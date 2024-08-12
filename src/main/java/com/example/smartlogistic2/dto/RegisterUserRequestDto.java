package com.example.smartlogistic2.dto;

import com.example.smartlogistic2.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RegisterUserRequestDto
{
    private String lastName;

    private String emailId;

    private String password;

    private String phoneNo;

    private String role;

    private String street;

    private String landmark;

    private String city;

    private String pincode;

    private String state;

    private String country;

    private int courierId; // courier id for delivery person

    public static User toUserEntity(RegisterUserRequestDto registerUserRequestDto) {
        User user = new User();
        BeanUtils.copyProperties(registerUserRequestDto, user);
        return user;
    }




}
