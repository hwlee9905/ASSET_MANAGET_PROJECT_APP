package org.example.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.manager.entity.Manager;
import org.example.domain.login.dto.LoginDto;
import org.example.domain.manager.repository.ManagerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService{
    private final ManagerRepository managerRepository;
    public void save(LoginDto loginDto) {
        Manager manager = new Manager(loginDto.getLoginId(), loginDto.getPassword(), loginDto.getName(), loginDto.getEmail());
        managerRepository.save(manager);
    }
}
