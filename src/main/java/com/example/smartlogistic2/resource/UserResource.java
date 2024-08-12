package com.example.smartlogistic2.resource;

import com.example.smartlogistic2.dto.CommonApiResponse;
import com.example.smartlogistic2.dto.RegisterUserRequestDto;
import com.example.smartlogistic2.entity.User;
import com.example.smartlogistic2.service.AddressService;
import com.example.smartlogistic2.service.UserService;
import com.example.smartlogistic2.utility.Constants;
import com.example.smartlogistic2.utility.Helper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.smartlogistic2.utility.Constants.ActiveStatus;

import java.util.Arrays;

@Component
@Transactional
public class UserResource
{
    private final Logger log = LoggerFactory.getLogger(UserResource.class);


    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public ResponseEntity<CommonApiResponse>  registerAdmin(RegisterUserRequestDto registerRequest)
    {
        log.info("Request recived for register Admin");

        CommonApiResponse response = new CommonApiResponse();

        if(registerRequest == null)
        {
            response.setResponseMessage("Request is empty");
            response.setSuccess(false);
            return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.BAD_REQUEST);
        }

        if(registerRequest.getEmailId() == null || registerRequest.getEmailId().isEmpty())
        {
            response.setResponseMessage("Email is empty");
            response.setSuccess(false);
            return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.BAD_REQUEST);
        }

        if(registerRequest.getPassword() == null || registerRequest.getEmailId() == null)
        {
            response.setResponseMessage("Password is empty");
            response.setSuccess(false);
            return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.BAD_REQUEST);
        }

        User existingUser = this.userService.getUserByEmailAndStatus(registerRequest.getEmailId(), Arrays.toString(ActiveStatus.ACTIVE.values()));

        if(existingUser != null)
        {
            response.setResponseMessage("User already exist with this email");
            response.setSuccess(false);
            return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.BAD_REQUEST);
        }

        User user = RegisterUserRequestDto.toUserEntity(registerRequest);


        user.setRole(Constants.UserRole.ROLE_ADMIN.value());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setStatus(Constants.ActiveStatus.ACTIVE.value());

        existingUser = this.userService.addUser(user);

        if(existingUser == null)
        {
            response.setResponseMessage("Admin registration failed");
            response.setSuccess(false);
            return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.BAD_REQUEST);
        }

        response.setResponseMessage("Admin registered successfully");
        response.setSuccess(true);
        log.info("Response sent");
        return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.OK);
    }

    public ResponseEntity<CommonApiResponse> registerUser(RegisterUserRequestDto request)
    {
        log.info("Request recived for register User");

        CommonApiResponse response = new CommonApiResponse();

        if(request == null)
        {
            response.setResponseMessage("Request is empty");
            response.setSuccess(false);
            return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.BAD_REQUEST);
        }

        User existingUser = this.userService.getUserByEmailAndStatus(request.getEmailId(), Arrays.toString(ActiveStatus.ACTIVE.values()));

        if(existingUser != null)
        {
            response.setResponseMessage("User already exist with this email");
            response.setSuccess(false);
            return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.BAD_REQUEST);
        }

        if(request.getRole() == null)
        {
            response.setResponseMessage("Role is empty");
            response.setSuccess(false);
            return new  ResponseEntity<CommonApiResponse>(response , HttpStatus.BAD_REQUEST);
        }
        User user = RegisterUserRequestDto.toUserEntity(request);

        if(user.getRole().equals(Constants.UserRole.ROLE_CUSTOMER.value())){
            user.setCustomerRefId(Helper.getAlphaNumericString(5));

        } else if (user.getRole().equals(Constants.UserRole.ROLE_COURIER.value())){
            user.setCourierRefId(Helper.getAlphaNumericString(5));
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        user.setStatus(Constants.ActiveStatus.ACTIVE.value());
        user.setPassword(encodedPassword);

        if(user.getRole().equals(Constants.UserRole.ROLE_DELIVERY.value()))
        {
            User courier = this.userService.getUserById(request.getCourierId());
            if (courier == null)
            {
                response.setResponseMessage("Courier not found");

            }
        }

return null;
    }




}
