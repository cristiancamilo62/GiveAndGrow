package com.giveandgrow.infrastructure.rest.controllers;


import com.giveandgrow.application.dto.PostulationDTO;
import com.giveandgrow.domain.ports.input.PostulationServicePort;
import com.giveandgrow.infrastructure.rest.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/postulations")
@RequiredArgsConstructor
public class PostulationController {

    private final PostulationServicePort postulationService;


    @GetMapping("/dummy")
    public PostulationDTO obtainDummy(){
        return new PostulationDTO();
    }

    @PostMapping()
    public ResponseEntity<Response<Void>> createPostulation(@RequestBody PostulationDTO postulationDTO){
        PostulationDTO postulation =  postulationService.postulate(postulationDTO.getUserId(),postulationDTO.getEventId());
        Response<Void> response = Response.success(HttpStatus.ACCEPTED,"Postulation successfully");
        return ResponseEntity.created(URI.create("api/v1/postulations" + postulation.getId())).body(response);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<Response<Void>> acceptPostulation(@PathVariable UUID id){
        postulationService.accept(id);
        Response<Void> response = Response.success(HttpStatus.ACCEPTED,"application successfully accepted");
        return ResponseEntity.ok(response);
    }
}
