package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SiteUser")
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long user_id;
    @Column(name ="Username", nullable = false)
    private String username;
    @Column(name ="Password", nullable = false)
    private String user_password;
    @Column(name = "JoinDate", nullable = false)
    private LocalDateTime user_join_date;
    @Column(name = "Status", nullable = false)
    private UserStatus user_status;
    @Column(name ="PhoneNumber", nullable = false)
    private String user_phone_number;
    @Column(name ="Email", nullable = false)
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

    public SiteUser(Long user_id, String username, String user_password, LocalDateTime user_join_date,
                    UserStatus user_status, String user_phone_number, String email, List<Role> roles) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.user_join_date = user_join_date;
        this.user_status = user_status;
        this.user_phone_number = user_phone_number;
        this.email = email;
        this.roles = roles;
    }
}
