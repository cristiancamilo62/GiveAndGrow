package com.giveandgrow.infrastructure.rest.controllers;

import com.giveandgrow.domain.ports.input.OrganizationServicePort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.giveandgrow.application.dto.OrganizationDTO;
import com.giveandgrow.shared.exception.GiveAndGrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/organizations")
public class OrganizateController {

    private final OrganizationServicePort organizationService;

    @GetMapping("/dummy")
    public OrganizationDTO dummy(){
        return new OrganizationDTO();
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody OrganizationDTO organizationDTO) {

        try {
            organizationService.createOrganization(organizationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Organization created successfully");
        } catch (GiveAndGrowException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }

}
