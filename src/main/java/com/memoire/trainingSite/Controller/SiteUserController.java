package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.DTO.UserDTO;
import com.memoire.trainingSite.DTO.UserResponseDTO;
import com.memoire.trainingSite.Services.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class SiteUserController {

    private SiteUserService siteUserService;

    @Autowired
    public SiteUserController(SiteUserService siteUserService){
        this.siteUserService = siteUserService ;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = siteUserService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDTO userDTO) {
        UserResponseDTO userResponseDTO = siteUserService.createUser(userDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED) ;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId){
        return  siteUserService.getUserById(userId)
                .map(userResponseDTO -> new ResponseEntity<>(userResponseDTO, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
    }
    @GetMapping("/search")
    public ResponseEntity<UserResponseDTO> getUserByUserName(@RequestParam String username){
        Optional<UserResponseDTO>  user = siteUserService.getUserByUsername(username);
        return user
                .map(userResponseDTO -> new ResponseEntity<>(userResponseDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        Optional<UserResponseDTO> updatedUser = siteUserService.updateUser(userId, userDTO);
        return updatedUser.map(userResponseDTO -> new ResponseEntity<>(userResponseDTO,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        siteUserService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }














/*
    //creating some dummy data
    @PostConstruct
    public void init(){
        List<SiteUser> users = new ArrayList<>();


            SiteUser user = new SiteUser();
            user.setUsername("customuser" );
            user.setUser_password("password");
            user.setUser_join_date(LocalDateTime.now());
            user.setUser_status(UserStatus.ACTIVE);
            user.setUser_phone_number("123-456-789" );
            user.setNotifications(new ArrayList<>());
            user.setAlerts(new ArrayList<>());



            siteUserService.createUser(user) ;

    }
*/


}
