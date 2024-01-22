package org.example.appproject_be.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appproject_be.dto.ManagerDto;
import org.example.appproject_be.model.Manager;
import org.example.appproject_be.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody ManagerDto managerDto, HttpServletRequest request) {
        Manager manager = loginService.login(managerDto.getLoginId(), managerDto.getPassword());
        if (manager == null) {
            return new ResponseEntity<>("LOGIN FAILED : 아이디 또는 비밀번호가 맞지 않습니다.", HttpStatus.SERVICE_UNAVAILABLE);
        }else{
            //세션이 있으면 세션 반환, 없으면 신규 세션을 생성
            HttpSession session = request.getSession();
            //세션에 로그인 회원 정보 저장
            session.setAttribute(ManagerDto.LOGIN_MANAGER, manager);
            return new ResponseEntity<>("LOGIN SUCCESS", HttpStatus.OK);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("LOGOUT SUCCESS", HttpStatus.OK);
    }
}
