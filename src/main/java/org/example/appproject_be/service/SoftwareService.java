package org.example.appproject_be.service;

import org.example.appproject_be.dto.SoftwareDto;
import org.example.appproject_be.model.Software;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface SoftwareService {
    Software saveSoftware(Software software);
    void deleteSoftware(Long Id);
    List<SoftwareDto> getSoftwares();
    SoftwareDto getSoftware(Long id);
}
