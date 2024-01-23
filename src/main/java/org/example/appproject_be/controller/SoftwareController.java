package org.example.appproject_be.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public SoftwareDto saveAsset(@Valid @RequestBody SoftwareDto software, HttpServletRequest request){
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }
        return softwareService.saveSoftware(software);
    }
    //localhost:8080/assets/software?idx=idx2123
    @DeleteMapping("/assets/software")
    public ResponseEntity<String> deleteAsset (@RequestParam("idx") Long idx, HttpServletRequest request){
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }

        softwareService.deleteSoftware(idx);
        return new ResponseEntity<>("해당 자산을 삭제하였습니다.", HttpStatus.OK);
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
    public SoftwareDto updateAsset(@RequestParam("swidx") Long swidx, @RequestParam("assetidx") Long assetidx,  @RequestBody SoftwareDto softwareDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false로 설정하면 세션이 없으면 null을 반환
        // 세션이 null이면 에러 반환
        if (session == null) {
            throw new IllegalStateException("해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
        }
        softwareDto.setSwidx(swidx);
        softwareDto.setAssetidx(assetidx);
        return softwareService.updateSoftware(softwareDto);
    }
    @GetMapping("/assets/softwares/sort") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<SoftwareDto> getAssets(@RequestParam("sortAttr") String sortAttr, @RequestParam("sortOrder") String sortOrder) {
        return softwareService.getSoftwares(sortAttr, sortOrder);
    }
}
