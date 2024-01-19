package org.example.appproject_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.dto.SoftwareDto;
import org.example.appproject_be.model.Software;
import org.example.appproject_be.service.SoftwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public SoftwareDto saveAsset(@Valid @RequestBody SoftwareDto software){
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
    @PutMapping("/assets/software/update")
    public SoftwareDto updateAsset(@RequestParam("swidx") Long swidx, @RequestParam("assetidx") Long assetidx,  @RequestBody SoftwareDto softwareDto) {
        softwareDto.setSwidx(swidx);
        softwareDto.setAssetidx(assetidx);
        return softwareService.updateSoftware(softwareDto);
    }
    @GetMapping("/assets/softwares/sort") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<SoftwareDto> getAssets(@RequestParam("sortAttr") String sortAttr, @RequestParam("sortOrder") String sortOrder) {
        return softwareService.getSoftwares(sortAttr, sortOrder);
    }
}
