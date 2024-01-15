package org.example.appproject_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.dto.SoftwareDto;
import org.example.appproject_be.model.Software;
import org.example.appproject_be.service.SoftwareService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController //@Controller + @ResponseBody
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class SoftwareController {
    private final SoftwareService softwareService;
    //localhost:8080/assets/software
    @PostMapping("/assets/software")
    public Software saveAsset(@Valid @RequestBody Software software){
        return softwareService.saveSoftware(software);
    }
    //localhost:8080/assets/software?idx=idx2123
    @DeleteMapping("/assets/software")
    public void deleteAsset (@RequestParam("idx") Long idx){
        softwareService.deleteSoftware(idx);
    }
    //localhost:8080/assets/softwares
    @GetMapping("/assets/softwares")
    public List<SoftwareDto> getAssets(){
        return softwareService.getSoftwares();
    }
    @GetMapping("/assets/software")
    public SoftwareDto getAsset(@RequestParam Long idx){
        return softwareService.getSoftware(idx);
    }
}
