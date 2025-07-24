package com.emranhss.project.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/")
@CrossOrigin("*")

public class UserRestController {

    @Autowired
    private UserService userService;


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
