package org.example.appproject_be.service;

import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HardwareService {
//    List<HardwareDto> findHardwareWithAssetInfo(Long Id);
    HardwareDto saveHardware(HardwareDto hardwareDto);
}
