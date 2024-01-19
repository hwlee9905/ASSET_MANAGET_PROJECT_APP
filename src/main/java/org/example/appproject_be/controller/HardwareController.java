package org.example.appproject_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@Controller + @ResponseBody
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
public class HardwareController {
    private final HardwareService hardwareService;

    @PostMapping("/assets/hardware")
    public HardwareDto saveAsset(@Valid @RequestBody HardwareDto hardware) {
        return hardwareService.saveHardware(hardware);
    }

    @DeleteMapping("/assets/hardware")
    public ResponseEntity<String> deleteAsset(@RequestParam("idx") Long idx) {

        hardwareService.deleteHardware(idx);
        return new ResponseEntity<>("해당 자산을 삭제하였습니다.", HttpStatus.OK);
    }

    @GetMapping("/assets/hardwares") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<HardwareDto> getAssets() {
        return hardwareService.getHardwares();
    }
    @GetMapping("/assets/hardwares/sort") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<HardwareDto> getAssets(@RequestParam("sortAttr") String sortAttr, @RequestParam("sortOrder") String sortOrder) {
        return hardwareService.getHardwares(sortAttr, sortOrder);
    }

    @GetMapping("/assets/hardware")
    public HardwareDto getAsset(@RequestParam Long idx){
        return hardwareService.getHardware(idx);
    }

    @PutMapping("/assets/hardware/update")
    public HardwareDto updateAsset(@RequestParam("hwidx") Long hwidx, @RequestParam("assetidx") Long assetidx,  @RequestBody HardwareDto hardwareDto) {
        hardwareDto.setHwidx(hwidx);
        hardwareDto.setAssetidx(assetidx);
        return hardwareService.updateHardware(hardwareDto);
    }

}
