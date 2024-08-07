package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Entity
@Table(name = "SiteUser")
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long user_id;
    @Column(name ="Username")
    private String username;
    @Column(name ="Password" )
    private String user_password;
    @Column(name = "JoinDate")
    private LocalDateTime user_join_date;
    @Column(name = "Status")
    private UserStatus user_status;
    @Column(name ="PhoneNumber")
    private String user_phone_number;
    @Column(name ="Email")
    private String email;



    @OneToMany(mappedBy = "id_notification.user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<Alert> alerts = new ArrayList<>() ;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(
            name = "SiteUser_Roles",
            joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();
}
