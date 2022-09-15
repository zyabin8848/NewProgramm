package com.example.SpringBootBasic.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserModel {
    private String firstName; // this variable must match with the .json file
    private String lastName;
    private String email;
    private String password;


    private List<RoleModel> role;

}


