package com.memoire.trainingSite.DTO;


import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token  ;
    private String tokentype = "Bearer" ;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

}
