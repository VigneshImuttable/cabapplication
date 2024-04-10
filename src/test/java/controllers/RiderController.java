package controllers;

import dtos.RiderSignUpDto;
import models.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repositories.RiderRepository;
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

    @GetMapping("/{id}")
    public Rider getRider(@PathVariable("id") Long riderId) {
        return RiderRepository.findById(riderId);
    }
}
