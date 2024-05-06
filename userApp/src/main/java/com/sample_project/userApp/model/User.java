package com.sample_project.userApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "UserDetails")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String userName;
    String email;
    String task;


}
