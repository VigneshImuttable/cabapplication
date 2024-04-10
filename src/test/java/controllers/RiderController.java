package controllers;

import dtos.RiderSignUpDto;
import models.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.RiderServices;

@RestController
@RequestMapping("/rider")
public class RiderController {

   private final RiderServices riderServices;

   @Autowired
   public RiderController(RiderServices riderServices){
       this.riderServices = riderServices;
   }

   @PostMapping("/add")
    public Rider addRider(@RequestBody RiderSignUpDto riderSignUpDto){

        String name = riderSignUpDto.getName();
        String sex = riderSignUpDto.getSex();
        Integer age = riderSignUpDto.getAge();

        return riderServices.addRider(name,age,sex);
    }


}
