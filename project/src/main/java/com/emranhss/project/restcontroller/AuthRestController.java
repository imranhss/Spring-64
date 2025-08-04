package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.AuthenticationResponse;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.ITokenRepository;
import com.emranhss.project.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @Autowired
    ITokenRepository tokenRepository;


    @PostMapping
    public ResponseEntity<Map<String, String>> saveUser(
            @RequestPart(value = "user") String userJson,
            @RequestParam(value = "photo") MultipartFile file
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);

        try {
            authService.saveOrUpdate(user, file);
            Map<String, String> response = new HashMap<>();
            response.put("Message", "User Added Successfully ");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Message", "User Add Faild " + e);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @GetMapping("all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = authService.findAll();
        return ResponseEntity.ok(users);

    }


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse>  login(@RequestBody User request){
        return ResponseEntity.ok(authService.authenticate(request));

    }


    @GetMapping("/active/{id}")
    public ResponseEntity<String> activeUser(@PathVariable("id") int id){

        String response= authService.activeUser(id);
        return  ResponseEntity.ok(response);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header.");
        }

        String token = authHeader.substring(7);  // Strip "Bearer "

        tokenRepository.findByToken(token).ifPresent(savedToken -> {
            savedToken.setLogout(true);  // Mark token as logged out
            tokenRepository.save(savedToken);
        });

        return ResponseEntity.ok("Logged out successfully.");
    }





}
