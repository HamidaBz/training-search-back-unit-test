package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.Services.SiteUserService;
import com.memoire.trainingSite.models.SiteUser;
import com.memoire.trainingSite.models.UserStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class SiteUserController {

    private SiteUserService siteUserService;

    //dependency injection
    @Autowired
    public SiteUserController(SiteUserService siteUserService){
        this.siteUserService = siteUserService ;
    }

    @GetMapping
    public List<SiteUser> getAllUsers(){
        return siteUserService.getAllUsers();
    }
    @GetMapping("/{id}")
    public Optional<SiteUser> getUserById(@PathVariable Long id){
        return  siteUserService.getById(id);
    }
    @PostMapping("/new")
    public SiteUser createUser(@RequestBody SiteUser siteUser) {
        return siteUserService.createUser(siteUser) ;
    }
    @PutMapping("/{id}")
    public SiteUser updateUser(@PathVariable Long id, @RequestBody SiteUser siteUser) {
        System.out.println("put request ");
        return siteUserService.updateUser(id, siteUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        siteUserService.deleteUser(id);
    }















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



}
