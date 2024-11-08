package com.apicokeshop.cokeshop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apicokeshop.cokeshop.Entities.Business;
import com.apicokeshop.cokeshop.Repositories.BusinessRepository;

@RestController
@RequestMapping("/business")
public class BusinessController {
    
    @Autowired
    private BusinessRepository businessRespository;

    @GetMapping //Find Everithing
    public List<Business> getAllBusiness(){
        return businessRespository.findAll();
    }

    @GetMapping("/{id}") //Find By ID
    public Business getBusinessById(@PathVariable Long id){
        return businessRespository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Business found with ID: " + id));
    }

    @PostMapping //save
    public Business createBusiness(@RequestBody Business business){
        return businessRespository.save(business);
    }

    @PutMapping("/{id}") //update
    public Business updateBusiness(@PathVariable Long id, @RequestBody Business detailBusiness){
        Business business = businessRespository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Business found with ID: " + id));

        business.setBusinessName(detailBusiness.getBusinessName());
        business.setEmail(detailBusiness.getEmail());
        business.setDescription(detailBusiness.getDescription());
        business.setLogo(detailBusiness.getLogo());
        business.setNumber(detailBusiness.getNumber());
        business.setAddress(detailBusiness.getAddress());
        business.setPassword(detailBusiness.getPassword());

        return businessRespository.save(business);
    }

    @DeleteMapping("/{id}") //Delete
    public String deleteBusiness(@PathVariable Long id){
        Business business = businessRespository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Business found with ID: " + id));

        businessRespository.delete(business);
        return "The Business with ID " + id + " was removed.";
    }
    
}

