package com.example.SpringBootBasic.controller;


import com.example.SpringBootBasic.entity.User;
import com.example.SpringBootBasic.model.UserModel;
import com.example.SpringBootBasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartApplicationController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/firstTest") // http://localhost:8080/cart/firsTest

    public String getData(){
        return "Hello SpringBoot";

    }
    @GetMapping(value = "/secondTest") //   http://localhost:8080/cart/secondTest
    public String getSecondData(){
        return "Second data from SpringBoot Application";

    }
/*
    @PostMapping(value = "/saveData") // http://localhost:8080/cart/saveData
    public String saveCustomerRecord(@RequestBody UserModel userModel ){
        System.out.println("Received First Name :" + userModel.getFirstName());
        System.out.println("Received Last Name :" + userModel.getLastName());
        System.out.println("Received Email :" + userModel.getEmail());



        boolean result = this.userService.saveUserData(userModel );
            if (result){
                return "data Save Successfully";
            } else {
                return "Data not saved" ;

            }

*/

        // save the database
    @PostMapping(value = "/saveData") //http://localhost:8081/cart/saveData
    public String saveCustomerRecord(@RequestBody List<UserModel> userModels) {
        System.out.println("The list Size is ::" +userModels.size());
        // System.out.println("Received First name : "+ userModel.getFirstName());

        Boolean result = this.userService.saveUserData(userModels);
        if (result) {
            return "Data Save Successfully";
        } else {
            return "Could not able to save data";
        }
    }
/*
    @PostMapping(value = "/saveEmployeeData")  // http://localhost/cart/saveEmployeeData
    public String saveEmpDetails( EmployeeModel employeeModel) {
        System.out.println("Recieved First Name " + employeeModel.getFirstName());
        boolean result = this.userService.saveEmpData(employeeModel);
        if (result) {
            return "data Save Successfully";
        } else {
            return " Data Not Saved";
        }
    }*/

    // fetch the data from database    // http://localhost:8081/cart/fetchUserInfo
    @GetMapping(value = "/fetchUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel fetchRecords(@RequestParam(name="userId") Integer userId) {
        User retrievedUser = this.userService.fetchuUerroleforselectedUser(userId);
        UserModel finalUserModel = this.userService.pupulateUserModel(retrievedUser);
        return finalUserModel;
    }

    //fetch all data from database  // http://localhost:8081/cart/fetchAllUserInfo
    @GetMapping(value = "/fetchAllUserInfo" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserModel> fetchAllUserInfo(){
        List <User> userList = this.userService.fetchDetailsAllUser();
        List<UserModel> userModels = this.userService.populateAllUserModel(userList);
        return userModels;
    }
}
