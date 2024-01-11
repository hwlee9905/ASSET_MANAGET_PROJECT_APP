package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.SoftwareDto;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.model.Software;
import org.example.appproject_be.repository.AssetRepository;
import org.example.appproject_be.repository.HardwareRepository;
import org.example.appproject_be.repository.SoftwareRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoftwareServiceImpl implements SoftwareService{
    private final SoftwareRepository softwareRepository;
    private final AssetRepository assetRepository;
    @Override
    public SoftwareDto saveSoftware(SoftwareDto softwareDto) {
        assetRepository.save(softwareDto.getAsset());
        Software software = softwareDto.getSoftware();
        software.setAsset(softwareDto.getAsset());
        softwareRepository.save(softwareDto.getSoftware());
        return softwareDto;
    }
}
