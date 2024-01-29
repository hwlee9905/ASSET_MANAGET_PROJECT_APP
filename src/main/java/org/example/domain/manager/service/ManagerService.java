package org.example.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.manager.entity.Manager;
import org.example.domain.manager.dto.ManagerDto;
import org.example.domain.manager.repository.ManagerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService{
    private final ManagerRepository managerRepository;
    public void save(ManagerDto managerDto) {
        Manager manager = new Manager(managerDto.getLoginId(), managerDto.getPassword(), managerDto.getName(), managerDto.getEmail());
        managerRepository.save(manager);
    }
}
