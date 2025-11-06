package com.mcp.newmcp.controlller;

import com.mcp.newmcp.service.PersonalInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalInfoController {

    private final PersonalInfoService personalInfoService;

    public PersonalInfoController(PersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/info")
    public ResponseEntity<String> getPersonalInfo(){
        return ResponseEntity.ok(personalInfoService.getInfo(false));
    }
}
