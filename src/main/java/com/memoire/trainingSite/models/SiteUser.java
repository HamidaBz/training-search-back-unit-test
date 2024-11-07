package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SiteUser")
public class SiteUser implements UserDetails {
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


    @Enumerated(EnumType.STRING)
    private Role role ;

    public SiteUser(Long user_id, String username, String user_password, LocalDateTime user_join_date,
                    UserStatus user_status, String user_phone_number, String email, Role role) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.user_join_date = user_join_date;
        this.user_status = user_status;
        this.user_phone_number = user_phone_number;
        this.email = email;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return user_password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
