package org.example.appproject_be.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public HardwareDto saveAsset(@Valid @RequestBody HardwareDto hardware, HttpServletRequest request) {
        // 세션 가져오기
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }
        return hardwareService.saveHardware(hardware);
    }

    @DeleteMapping("/assets/hardware")
    public ResponseEntity<String> deleteAsset(@RequestParam("idx") Long idx, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }
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
    public HardwareDto updateAsset(@RequestParam("hwidx") Long hwidx, @RequestParam("assetidx") Long assetidx,  @RequestBody HardwareDto hardwareDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }
        hardwareDto.setHwidx(hwidx);
        hardwareDto.setAssetidx(assetidx);
        return hardwareService.updateHardware(hardwareDto);
    }

}
