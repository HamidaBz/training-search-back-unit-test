package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.UserDTO;
import com.memoire.trainingSite.DTO.UserResponseDTO;
import com.memoire.trainingSite.models.SiteUser;
import org.springframework.stereotype.Component;

@Component
public class SiteUserMapper {
    public SiteUser toEntity(UserDTO siteUserDTO){
        return new SiteUser(
                siteUserDTO.getUser_id(),
                siteUserDTO.getUsername(),
                siteUserDTO.getPassword(),
                siteUserDTO.getUser_join_date(),
                siteUserDTO.getUser_status(),
                siteUserDTO.getUser_phone_number(),
                siteUserDTO.getEmail(),
                siteUserDTO.getRoles());
    }
    public UserResponseDTO toResponseDTO(SiteUser siteuser){
        return new UserResponseDTO(
                siteuser.getUser_id(),
                siteuser.getUsername(),
                siteuser.getUser_join_date(),
                siteuser.getUser_status(),
                siteuser.getUser_phone_number(),
                siteuser.getEmail(),
                siteuser.getRoles()
        );
    }
}
