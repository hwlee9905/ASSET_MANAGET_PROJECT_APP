package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.repository.AssetRepository;
import org.example.appproject_be.repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HardwareServiceImpl implements HardwareService{
    private final HardwareRepository hardwareRepository;
    private final AssetRepository assetRepository;
    @Override
    public HardwareDto saveHardware(HardwareDto hardwareDto) {

        assetRepository.save(hardwareDto.getAsset());
        Hardware hardware = hardwareDto.getHardware();
        hardware.setAsset(hardwareDto.getAsset());
        hardwareRepository.save(hardwareDto.getHardware());
        return hardwareDto;
    }
}
