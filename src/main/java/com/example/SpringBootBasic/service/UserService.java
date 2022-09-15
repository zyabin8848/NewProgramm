package com.example.SpringBootBasic.service;

import com.example.SpringBootBasic.entity.Role;
import com.example.SpringBootBasic.entity.User;
import com.example.SpringBootBasic.model.RoleModel;
import com.example.SpringBootBasic.model.UserModel;
import com.example.SpringBootBasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service   // this is to identify
public class UserService {

    public UserService() {

    }

    @Autowired
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean saveUserData(List<UserModel> userModels) {
        try {
            for (UserModel userModel : userModels) {
                User user = new User();
                user.setFirstName(userModel.getFirstName());

                user.setLastName(userModel.getLastName());
                user.setEmail(userModel.getEmail());
                user.setPassword(userModel.getPassword());

                List<RoleModel> roleModels = userModel.getRole();

                for (RoleModel roleModel : roleModels) {
                    Role role = new Role();
                    role.setDept(roleModel.getDept());
                    role.setName(roleModel.getName());
                    user.addRoles(role);
                }
                this.userRepository.save(user);

            }
        } catch (Exception e) {
            System.err.println("Error Details" + e.getMessage());


        }
        return true;


    }

  /*  public Boolean saveEmpData( EmployeeModel employeeModel) {


        Employee employee = new Employee();
        employee.setEmpId(employeeModel.getEmpId());
        employee.setFirstName(employeeModel.getFirstName());
        employee.setLastName(employeeModel.getLastName());
        employee.setSalary(employeeModel.getSalary());

        try {
            this.employeeRepository.save(employee);
        }catch (Exception e){
            System.err.println("Error is : "    + e.getMessage());
        }
        return true;
    }*/


    public User fetchuUerroleforselectedUser(Integer userId) {
        User user = new User();
        Optional<User> result = this.userRepository.findById(userId);
        if (result != null && !result.isEmpty()) {
            user =result.get();

        }
        return user;


    }

    public UserModel pupulateUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setPassword(user.getPassword());

        List<RoleModel> roleModels = new ArrayList<>();
        Set<Role> roleList = user.getRoles();
        for( Role role : roleList  ){
             RoleModel roleModel = new RoleModel();
             roleModel.setName(role.getName());
             roleModel.setDept(role.getDept());
             roleModels.add(roleModel);
        }
        userModel.setRole(roleModels);
        return userModel;

    }
    public List<User> fetchDetailsAllUser(){
        List<User> result = (List<User>) this.userRepository.findAll() ;
        if(result !=null && !result.isEmpty()){
            return result;

        } else {
            return new ArrayList<>(); // this is empty arratList
        }
    }

    public List<UserModel> populateAllUserModel(List<User> userList){
        List<UserModel> userModels = new ArrayList<>();
        for(User user : userList){
            UserModel userModel = new UserModel();
            userModel.setEmail(user.getEmail());
            userModel.setFirstName(userModel.getFirstName());
            userModel.setLastName(user.getLastName());
            userModel.setPassword(user.getPassword());

            List<RoleModel> allRoleModel = new ArrayList<>();
            Set<Role> roleList = user.getRoles();

            for(Role role : roleList){
                RoleModel roleModel = new RoleModel();
                roleModel.setName(role.getName());
                roleModel.setName(role.getName());
                allRoleModel.add(roleModel);

            }
            userModel.setRole(allRoleModel);
            userModels.add(userModel);


        }
        return userModels;
    }



}
