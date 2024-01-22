package org.example.appproject_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.ManagerDto;
import org.example.appproject_be.dto.SoftwareDto;
import org.example.appproject_be.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class ManagerController {
    private final ManagerService managerService;
    @PostMapping("/manager")
    public ResponseEntity<String> saveManager(@Valid @RequestBody ManagerDto managerDto){
        managerService.save(managerDto);
        return new ResponseEntity<>("관리자 아이디 생성 성공", HttpStatus.OK);
    }
}
