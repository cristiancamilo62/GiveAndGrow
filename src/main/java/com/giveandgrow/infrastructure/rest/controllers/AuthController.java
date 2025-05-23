package com.giveandgrow.infrastructure.rest.controllers;

import org.springframework.security.core.Authentication;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.giveandgrow.infrastructure.security.jwt.JwtService;
import com.giveandgrow.infrastructure.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<Map<String , Object>> login(@RequestBody Map<String, String> request) {
        
        Authentication  authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password"))
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"));

        String token = jwtService.generateToken(userDetails, role,userDetails.getId());
        


        Map<String, Object> response = Map.of(
            "token", token,
            "AuthenticatedUser", userDetails.getResponseAuth()
        );

        return ResponseEntity.ok(response);
    }
    
}
