package com.example.SpringBootBasic.entity;




import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="User_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer custId;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
/*

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,orphanRemoval = true) //
    private List<Role> roles = new ArrayList<Role>();
*/


    @ManyToMany( cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinTable(name = "user_role" ,
                        joinColumns = {@JoinColumn(name = "custId")} ,
                        inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roles  = new HashSet<Role>();


    /*public void addRoles(Role role ) {
        this.roles.add(role);
        role.setUserList(this);

    }
*/
    public void addRoles(Role role ) {
        this.roles.add(role);
        role.getUserList().add(this);

    }


}
