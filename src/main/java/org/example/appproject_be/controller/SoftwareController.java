package org.example.appproject_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.dto.SoftwareDto;
import org.example.appproject_be.service.SoftwareService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SoftwareController {
    private final SoftwareService softwareService;
    @PostMapping("/assets/software")
    public SoftwareDto saveAsset(@Valid @RequestBody SoftwareDto softwareDto){
        return softwareService.saveSoftware(softwareDto);
    }
}
