package com.wzx;

import com.wzx.domain.Role;
import com.wzx.domain.User;
import com.wzx.repository.RoleRepository;
import com.wzx.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;

/**
 * Created by arthurwang on 17/3/1.
 */
@Service
public class DataInit {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    public DataInit(UserRepository userRepository,
                    RoleRepository roleRepository,
                    BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void dataInit() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("12"));
        admin.setPasswordConfirm(bCryptPasswordEncoder.encode("12"));

        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);
        Role userRole = new Role("USER");
        roleRepository.save(userRole);

        admin.setRoles(Arrays.asList(adminRole));

        userRepository.save(admin);
    }
}
