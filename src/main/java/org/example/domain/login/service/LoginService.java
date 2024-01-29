package org.example.domain.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.manager.entity.Manager;
import org.example.domain.manager.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final ManagerRepository managerRepository;
    public Manager login(String loginId, String password) {
        log.info("login Id = " + loginId);
        Optional<Manager> optionalManager = managerRepository.findByLoginid(loginId);
        return optionalManager.filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
