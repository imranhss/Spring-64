package com.emranhss.project.restcontroller;

import com.emranhss.project.service.UserService;
import com.emranhss.project.entity.User;
<<<<<<< Updated upstream
=======
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
>>>>>>> Stashed changes
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
=======
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin("*")
>>>>>>> Stashed changes
public class UserRestController {

    @Autowired
    private UserService userService;

<<<<<<< Updated upstream
    @PostMapping
    public ResponseEntity<String> saveOrUpdate(@RequestBody User user) {
        try {
            userService.saveOrUpdate(user);
            return ResponseEntity.ok("Data Saved");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
=======
//    @PostMapping
//    public ResponseEntity<String> saveOrUpdate(@RequestBody User user) {
//        try {
//            userService.saveOrUpdate(user);
//            return ResponseEntity.ok("Data Saved");
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveUser(
            @RequestPart(value = "user") String userJson,
            @RequestParam(value = "photo") MultipartFile file
    ) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        User user=objectMapper.readValue(userJson, User.class);

        try{
            userService.saveOrUpdate(user, file);
            Map<String, String> response=new HashMap<>();
            response.put("Message", "User Added Successfully ");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){

            Map<String, String> errorResponse=new HashMap<>();
            errorResponse.put("Message", "User Add Faild "+e);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }



    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users=userService.findAll();
        return ResponseEntity.ok(users);
>>>>>>> Stashed changes
    }




}
