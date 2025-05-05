package com.giveandgrow.infrastructure.security.service;

import com.giveandgrow.domain.ports.output.OrganizationRepositoryPort;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort patientRepository;
    private final OrganizationRepositoryPort organizationRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      
      // 1) Intentamos cargar un usuario normal
      return patientRepository.findByEmail(username)
        .map(u -> new org.springframework.security.core.userdetails.User(
             u.getEmail(),
             u.getPassword(),
             List.of(new SimpleGrantedAuthority("ROLE_USER"))
          ))
        // 2) Si no existe, intentamos cargar una organización
        .orElseGet(() -> organizationRepositoryPort.findByEmail(username)
          .map(o -> new org.springframework.security.core.userdetails.User(
               o.getEmail(),
               o.getPassword(),
               List.of(new SimpleGrantedAuthority("ROLE_ORG"))
            ))
          .orElseThrow(() -> 
             new UsernameNotFoundException("No user or organization with email: " + username)
          )
        );
    }
}