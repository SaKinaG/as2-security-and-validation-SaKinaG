package com.securityAndLogging.hw.repo;

import com.securityAndLogging.hw.entity.XUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<XUser, Long> {

    XUser findByUsername(String username);
}
