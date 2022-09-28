package com.elcom.controller;

import com.elcom.auth.CustomUserDetails;
import com.elcom.auth.LoginRequest;
import com.elcom.auth.LoginResponse;
import com.elcom.auth.jwt.JwtTokenProvider;
import com.elcom.exception.ValidationException;
import com.elcom.model.User;
import com.elcom.service.UserService;
import com.elcom.utils.JSONConverter;
import com.elcom.validation.UserValidation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static javax.swing.text.html.HTML.Tag.OL;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@RestController
@RequestMapping(value = "/api")
public class UserManageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManageController.class);

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "lay danh sach user ", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công, có dữ liệu"),
            @ApiResponse(code = 204, message = "Thành công, không có dữ liệu"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy"),
            @ApiResponse(code = 500, message = "Lỗi BackEnd")
    })
    public ResponseEntity<List<User>> findAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer rowsPerPage,
                                              @RequestParam(defaultValue = "id") String sort) {
        List<User> userList = userService.findAll(currentPage, rowsPerPage, sort);
        if(userList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }
    @RequestMapping(value = "/users/{userID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getById(@PathVariable("userID") Long userID)
    {
        LOGGER.info("userID[{}]", userID);

        if(userID == null || userID.equals(OL))
            throw new ValidationException("userID khoong duoc de trong");

        User user = userService.findById(userID);
        LOGGER.info("user[{}]", userID);

        if(user == null)
            return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder builder)
    {
        LOGGER.info("{}", JSONConverter.toJSON(user));

        new UserValidation().validateUpsertUser(user, "INSERT");

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/users/{userID}").buildAndExpand(user.getUserID()).toUri());

        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @RequestMapping(value = "/users/{userID}", method = RequestMethod.PUT)
    public ResponseEntity<User>update(@PathVariable("userID") Long userID,@RequestBody User user) throws Exception {
        LOGGER.info("userID[{}] - {}",userID,JSONConverter.toJSON(user));

        if(userID == null|| userID.equals(OL)) {
            throw new ValidationException("userID khong duoc de trong");
        }
        new UserValidation().validateUpsertUser(user, "UPDATE");

        User currentUser = userService.findById(id);

        if ( currentUser==null )
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        currentUser.setFullName(user.getFullName());
        if( !StringUtil.isNullOrEmpty(user.getPassword()) )
            currentUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userService.save(currentUser);

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
