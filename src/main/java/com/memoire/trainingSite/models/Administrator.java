package com.memoire.trainingSite.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Administrator")
public class Administrator extends SiteUser {

}
