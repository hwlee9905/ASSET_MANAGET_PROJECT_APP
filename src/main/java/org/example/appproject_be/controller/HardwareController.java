package org.example.appproject_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class HardwareController {
    private final HardwareService hardwareService;
    @PostMapping("/assets/hardware")
    public HardwareDto saveAsset(@Valid @RequestBody HardwareDto hardwareDto){
        return hardwareService.saveHardware(hardwareDto);
    }
}
