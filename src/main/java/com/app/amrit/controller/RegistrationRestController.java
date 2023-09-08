package com.app.amrit.controller;

import com.app.amrit.constants.AppConstants;
import com.app.amrit.service.RegistrationService;
import com.app.amrit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RegistrationRestController {

    @Autowired
    private RegistrationService regService;

    @GetMapping("/emailcheck/{email}")
    public String checkEmail(@PathVariable String email) {
        boolean uniqueEmail = regService.uniqueEmail(email);
        if (uniqueEmail) {
            return AppConstants.UNIQUE;
        } else {
            return AppConstants.DUPLICATE;
        }
    }

    @GetMapping("/countries")
    public Map<Integer, String> getCountries() {
        Map<Integer, String> countries = regService.getCountries();
        return countries;
    }

    @GetMapping("/states/{countryId}")
    public Map<Integer, String> getStates(@PathVariable Integer countryId) {
        return regService.getStates(countryId);
    }

    @GetMapping("/cities/{stateId}")
    public Map<Integer, String> getCities(@PathVariable Integer stateId) {
        Map<Integer, String> cities = regService.getCities(stateId);
        return cities;
    }

    @PostMapping("/saveuser")
    public String saveUser(@RequestBody User user) {
        boolean registerUser = regService.registerUser(user);
        if (registerUser) {
            return AppConstants.SUCCESS;
        } else {
            return AppConstants.FAIL;
        }
    }
}