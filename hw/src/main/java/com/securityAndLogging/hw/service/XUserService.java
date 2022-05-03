package com.securityAndLogging.hw.service;

import com.securityAndLogging.hw.entity.XUser;
import com.securityAndLogging.hw.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class XUserService {

    private static final Logger log = LoggerFactory.getLogger(XUserService.class);


    private final UserRepository userRepository;

    public XUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(XUser xUser){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        String encoded = bCryptPasswordEncoder.encode(xUser.getPassword());
        xUser.setPassword(encoded);
        xUser.setRole("USER");
        log.info("This user will be saved " + xUser);
        userRepository.save(xUser);
    }
}
