
package com.giveandgrow.infrastructure.security.service;

import com.giveandgrow.domain.ports.output.OrganizationRepositoryPort;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort patientRepository;
    private final OrganizationRepositoryPort organizationRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patientRepository.findByEmail(username)
                .map(user -> new CustomUserDetails(
                        user.getEmail(),
                        user.getPassword(),
                        true, true, true, true,
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())),
                        user.getId(),
                        user.getRole(),
                        Map.of("identification",user.getIdentification(),
                                "firstName", user.getFirstName(),
                                "middleName", user.getMiddleName(),
                                "lastName", user.getLastName(),
                                "middleLastName", user.getMiddleLastName(),
                                "email", user.getEmail(),
                                "phoneNumber", user.getPhoneNumber(),
                                "password", user.getPassword()
                        )
                                
                ))
                .orElseGet(() -> organizationRepositoryPort.findByEmail(username)
                        .map(org -> new CustomUserDetails(
                                org.getEmail(),
                                org.getPassword(),
                                true, true, true, true,
                                List.of(new SimpleGrantedAuthority("ROLE_" + org.getRole())),
                                org.getId(),
                                org.getRole(),
                                Map.of("identification",org.getName(),
                                        "description", org.getDescription(),
                                        "contactNumber", org.getContactNumber(),
                                        "email", org.getEmail(),
                                        "password", org.getPassword(),  
                                        "address", org.getAddress()
                                        )
                                
                        ))
                        .orElseThrow(() ->
                                new UsernameNotFoundException("No user or organization with email: " + username)
                        ));
    }
}