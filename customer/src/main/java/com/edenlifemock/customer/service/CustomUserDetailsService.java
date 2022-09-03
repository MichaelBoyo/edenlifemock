package com.edenlifemock.customer.service;

import com.edenlifemock.customer.Customer;
import com.edenlifemock.customer.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load customer by email called with email --> {}", username);

        var customer = userRepository.findCustomersByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("user with username "+username+" not found"));
        log.info("found customer --> {}", customer);

        var details = new org.springframework.security.core.userdetails.User(
                customer.getUsername(),
                customer.getPassword(),
                getAuthorities(customer)
        );
        log.info("details --> {}", details);
        return details;
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Customer customer) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        customer.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        });
        return authorities;
    }
}
