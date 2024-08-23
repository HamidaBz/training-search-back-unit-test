package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.DTO.UserDTO;
import com.memoire.trainingSite.DTO.UserResponseDTO;
import com.memoire.trainingSite.mappers.SiteUserMapper;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.SiteUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SiteUserService {
    private SiteUserRepo siteUserRepo ;
    private SiteUserMapper siteUserMapper;
    @Autowired
    public SiteUserService(SiteUserRepo siteUserRepo, SiteUserMapper siteUserMapper){
        this.siteUserRepo = siteUserRepo ;
        this.siteUserMapper = siteUserMapper;
    }

    public UserResponseDTO createUser(UserDTO userDTO) {
        SiteUser user = siteUserMapper.toEntity(userDTO);
        return siteUserMapper.toResponseDTO(siteUserRepo.save(user));
    }


    public List<UserResponseDTO> getAllUsers() {
        return siteUserRepo.findAll().stream()
                .map(siteUserMapper::toResponseDTO).collect(Collectors.toList()) ;
    }

    public Optional<UserResponseDTO> getUserById(Long id) {
        return siteUserRepo.findById(id).map(siteUserMapper::toResponseDTO);
    }

    public Optional<UserResponseDTO> getUserByUsername(String username){
        return siteUserRepo.findByUsername(username).map(siteUserMapper::toResponseDTO);

    }

    public Optional<UserResponseDTO> updateUser(Long id, UserDTO userDTO) {
        Optional<SiteUser> user = siteUserRepo.findById(id);
        if (user.isPresent()) {
            SiteUser updateduser = siteUserMapper.toEntity(userDTO);
            siteUserRepo.save(updateduser);
            return Optional.of(siteUserMapper.toResponseDTO(updateduser));
        }
        return Optional.empty();
    }

    public void deleteUser(Long id) {

        if (siteUserRepo.existsById(id)) {
            siteUserRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
    }
}
