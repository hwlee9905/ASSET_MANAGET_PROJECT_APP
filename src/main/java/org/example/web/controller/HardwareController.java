package org.example.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.hardware.dto.HardwareAssignDto;
import org.example.domain.hardware.dto.HardwareDto;
import org.example.domain.hardware.dto.request.SaveHardwareDtoRequest;
import org.example.domain.hardware.dto.response.GetHardwaresDtoResponse;
import org.example.domain.hardware.service.HardwareService;
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
    public ResponseEntity<String> saveAsset(@Valid @RequestBody SaveHardwareDtoRequest saveHardwareDtoRequest, HttpServletRequest request) {
        // 세션 가져오기
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }
        hardwareService.saveHardware(saveHardwareDtoRequest);
        return new ResponseEntity<>("해당 자산을 등록하였습니다.", HttpStatus.OK);
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

    @GetMapping("/assets/hardwares")
    public List<GetHardwaresDtoResponse> getAssets() {
        log.info("in hardwares");
        return hardwareService.getHardwares();
    }
    @GetMapping("/assets/hardwares/sort")
    public List<GetHardwaresDtoResponse> getAssets(@RequestParam("sortAttr") String sortAttr, @RequestParam("sortOrder") String sortOrder) {
        return hardwareService.getHardwares(sortAttr, sortOrder);
    }

    @GetMapping("/assets/hardware")
    public GetHardwaresDtoResponse getAsset(@RequestParam Long idx){
        return hardwareService.getHardware(idx);
    }

    @PutMapping("/assets/hardware/update")
    public ResponseEntity<String> updateAsset(@RequestParam("hwidx") Long hwidx, @RequestParam("assetidx") Long assetidx, @RequestBody HardwareDto hardwareDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }
        hardwareDto.setHwidx(hwidx);
        hardwareDto.setAssetidx(assetidx);
        hardwareService.updateHardware(hardwareDto);
        return new ResponseEntity<>("해당 자산의 수정이 완료되었습니다.", HttpStatus.OK);
    }
    @PostMapping("/assets/hardware/assign")
    public ResponseEntity<String> assignAsset(@RequestParam("hwidx") Long hwidx, @RequestParam("assetidx") Long assetidx, @RequestBody HardwareAssignDto hardwareAssignDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }
        hardwareAssignDto.setHwidx(hwidx);
        hardwareAssignDto.setAssetidx(assetidx);
        hardwareService.assignHardware(hardwareAssignDto);
        return new ResponseEntity<>("해당 자산의 할당이 완료되었습니다.", HttpStatus.OK);
    }
}
