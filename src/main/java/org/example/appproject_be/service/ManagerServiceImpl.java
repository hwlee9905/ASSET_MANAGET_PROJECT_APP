package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.ManagerDto;
import org.example.appproject_be.model.Manager;
import org.example.appproject_be.repository.ManagerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    @Override
    public void save(ManagerDto managerDto) {
        Manager manager = new Manager(managerDto.getLoginId(), managerDto.getPassword(), managerDto.getName(), managerDto.getEmail());
        managerRepository.save(manager);
    }
}
