package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appproject_be.model.Manager;
import org.example.appproject_be.repository.ManagerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final ManagerRepository managerRepository;
    @Override
    public Manager login(String loginId, String password) {
        log.info("login Id = " + loginId);
        Optional<Manager> optionalManager = managerRepository.findByLoginid(loginId);
        return optionalManager.filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
