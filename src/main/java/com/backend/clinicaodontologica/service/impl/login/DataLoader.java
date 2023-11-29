package com.backend.clinicaodontologica.service.impl.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordUser  = passwordEncoder.encode("Pru.eba1");
        String passwordAdmin  = passwordEncoder.encode("Pru.eba.admin");

        userRepository.save(new AppUser("pedro","pedroUSer", "pedro@mail.com",passwordUser, AppUsuarioRoles.USER));
        userRepository.save(new AppUser("amanda","amandaAdmin", "amanda.admin@mail.com",passwordAdmin, AppUsuarioRoles.ADMIN));
    }
}
