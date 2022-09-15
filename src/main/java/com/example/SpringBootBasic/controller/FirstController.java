package com.example.SpringBootBasic.controller;

import com.example.SpringBootBasic.model.UserModel;
import org.springframework.web.bind.annotation.*;

/**
 * GET / PUT / POST / DELETE
 */

@RestController
@RequestMapping("/basic")
public class FirstController {
    @RequestMapping("/test/example")  // http://localhost:8080//basic//test
    public String test() {
        return "Hello world";
    }




    // @RequestMapping(value = "/first" ,method = RequestMethod.GET) // http://localhost:8080/basic/saveData
    @GetMapping( value = "/first/{id}")  //  http://localhost:8080/first/12   can use this instead of @RequestMapping
    public String getFirstData (@PathVariable("id")float id) { // @GerMapping is always fetch the data
        System.out.println("This is the ID from user :: " + id );
       /* *//*int convertedId = Integer.parseInt(id);
        S*//*ystem.out.println("Converted value::" + convertedId);*/

        return " This is  the First Spring Boot Application . Here is the good things " +
                "Run Successfull ";

    }

}
