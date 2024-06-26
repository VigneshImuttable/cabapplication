package com.create.cabapplication.controllers;

import com.create.cabapplication.dtos.RiderSignUpDto;
import com.create.cabapplication.models.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.create.cabapplication.repositories.RiderRepository;
import com.create.cabapplication.services.RiderServices;

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

    @GetMapping("/{id}")
    public Rider getRider(@PathVariable("id") Long riderId) {
        return riderServices.findRider(riderId);
    }




}
