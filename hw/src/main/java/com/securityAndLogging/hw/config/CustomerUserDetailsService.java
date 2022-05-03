package com.securityAndLogging.hw.config;

import com.securityAndLogging.hw.entity.XUser;
import com.securityAndLogging.hw.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomerUserDetailsService.class);

    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        XUser xUser = userRepository.findByUsername(username);
       if (xUser == null){
            log.error("User not found: " + username );
            throw new UsernameNotFoundException("User cannot be found");
       }
        return new XUserDetails(xUser);
    }

}
